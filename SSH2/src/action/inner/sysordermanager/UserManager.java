package action.inner.sysordermanager;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Power;
import bean.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import service.PowerService;
import service.UserService;

@Controller @Scope("prototype")
public class UserManager extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    private static final long serialVersionUID = 1L;

	@Resource
	UserService userService;
	@Resource
	PowerService powerService;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String userLeval;      //表示哪一级的用户
    private String userId;          //用户ID
    private String userName;        //用户名称
    private String name;            //用户姓名
    private String innerId;         //内部工号
    private String password;        //密码
    private String userOrderList;   //权限列表

    public String getUserOrderList() {
        return userOrderList;
    }
    public void setUserOrderList(String userOrderList) {
        this.userOrderList = userOrderList;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInnerId() {
        return innerId;
    }
    public void setInnerId(String innerId) {
        this.innerId = innerId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	public String getUserLeval() {
		return userLeval;
	}
	public void setUserLeval(String userLeval) {
		this.userLeval = userLeval;
	}
	public void setServletResponse(HttpServletResponse res) {
        // TODO Auto-generated method stub
        this.response = res;
    }
    public void setServletRequest(HttpServletRequest req) {
        // TODO Auto-generated method stub
        this.request = req;
    }

    /**
     * 查询省级用户1，市级用户2，县级用户3
	 *
     */
    public String SelectUserLevalOne(){
        response.setContentType("json/javascript;charset=utf-8");

		List users = userService.getUsers();
		JSONArray json = new JSONArray();
		for (Object o : users){
			User user = (User) o;
			if (user.getLeval().equals(this.userLeval)){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("id",user.getId().toString());
				map.put("name",user.getUserName());
				json.add(map);
			}
		}
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询一个用户的详细信息,使用userId
     */
    public String UserInfo(){
        response.setContentType("json/javascript;charset=utf-8");

        HashMap<String, String> map = new HashMap<String, String>();

		User user = userService.getUser(Integer.parseInt(this.userId));
		map.put("userName", user.getUserName());
        map.put("name", user.getName());
        map.put("innerId", user.getInnerId());
        map.put("passWord", user.getPassword());

        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除 一个用户,使用userId
     */
    public String DeleteUser(){
        response.setContentType("json/javascript;charset=utf-8");

		User user = userService.getUser(Integer.parseInt(this.userId));
		boolean tag = userService.deleteUser(user);

		HashMap<String, String> map = new HashMap<String, String>();
		if (tag)
			map.put("state", "success");
		else
			map.put("state", "failed");

        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增或更改一个用户的信息,userId为-1，则新增用户
     */
    public String UpdateUser(){
        response.setContentType("json/javascript;charset=utf-8");

		String tag = "failed";
		if (this.userId.equals("-1")){
			User user = new User();
			user.setUserName(this.userName);
			user.setName(this.name);
			user.setInnerId(this.innerId);
			user.setPassword(this.password);
			boolean a = userService.addUser(user);
			boolean b = userService.updateUser(user);
			if (a && b)
				tag = "success";

		} else {
			User user = userService.getUser(Integer.parseInt(this.userId));
			if (user != null){
				user.setUserName(this.userName);
				user.setName(this.name);
				user.setInnerId(this.innerId);
				user.setPassword(this.password);
				boolean a = userService.updateUser(user);
				if (a)
					tag = "success";
			}
		}

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有权限，对当前用户(userId)已有的权限进行标记
     */
    public String SelectUserOrders(){
        response.setContentType("json/javascript;charset=utf-8");

		User user = userService.getUser(Integer.parseInt(this.userId));
		Set userPower = user.getPower();
		List allPowers = powerService.getPowers();
		JSONArray json = new JSONArray();
		for (Object o : allPowers){
			Power power = (Power) o;
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id",power.getId().toString());
			map.put("name",power.getPowerName());
			if (userPower.contains(power))
				map.put("own","1");
			else
				map.put("own","0");

			json.add(map);
		}
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存对用户userId权限userOrderList的更改
     */
    public String UpdateUserOrderChange(){
        response.setContentType("json/javascript;charset=utf-8");

		String[] split = userOrderList.split("\\|");
		String tag = "failed";

		User user = userService.getUser(Integer.parseInt(this.userId));
		if (user != null){
			user.getPower().clear();
			userService.updateUser(user);
			if (!userOrderList.equals("")){
				for (String s : split){
					Power power = powerService.getPower(Integer.parseInt(s));
					power.addUser(user);
					powerService.updatePower(power);
				}
			}
			tag = "success";
		}

		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}










