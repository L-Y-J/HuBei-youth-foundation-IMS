package action.inner.sysordermanager;

import java.io.IOException;
import java.util.ArrayList;
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

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import service.PowerService;
import service.UserService;

@Controller @Scope("prototype")
public class OrderManager extends ActionSupport {
    private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;
	@Resource
	private PowerService powerService;

    /**
     * 响应查询用户列表的操作
     * @return
     */
    public String List(){
        HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray json = new JSONArray();

        try {
			List users = userService.getUsers();
			for (Object o : users){
				User user = (User) o;
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id",user.getId().toString());
				map.put("name",user.getUserName());
				json.add(map);
			}
            response.setContentType("json/javascript;charset=utf-8");
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 响应查询某个用户权限的操作
     * @return
     */
    public String Order(){
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        //用户id
        String userId = request.getParameter("id");
        //用户名称
        String userName = request.getParameter("name");

		JSONArray json = new JSONArray();
		User user = userService.getUser(Integer.parseInt(userId));
		Set set = user.getPower();
		for (Object o : set){
			Power power = (Power) o;
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id",power.getId().toString());
			map.put("name",power.getPowerName());
			json.add(map);
		}
        response.setContentType("json/javascript;charset=utf-8");
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回某个用户的一个权限的详细信息
     * @return
     */
    public String OneOrder(){
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        //用户id
        String userId = request.getParameter("userId");
        //用户名称
        String userName = request.getParameter("userName");
        //用户的权限id
        String orderId = request.getParameter("orderId");

		User user = userService.getUser(Integer.parseInt(userId));
		Power power = null;
		Set set = user.getPower();
		for (Object o : set){
			Power p = (Power) o;
			if (p.getId().toString().equals(orderId))
				power = p;
		}

		HashMap<String, String> map = new HashMap<String, String>();
        map.put("orderId", power.getId().toString());
        map.put("orderName", power.getPowerName());
        map.put("remark", power.getMark());
        JSONObject json = JSONObject.fromObject(map);

        response.setContentType("json/javascript;charset=utf-8");
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 查询系统所有权限
     * return
	 */
	public String OrderList(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/javascript;charset=utf-8");

		List powers = powerService.getPowers();
		JSONArray jsonArray = new JSONArray();
		for (Object o : powers){
			Power power = (Power) o;
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id",power.getId().toString());
			map.put("name",power.getPowerName());
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 为某一个用户增加一个权限
     * @return
     */
    public String AddOrder(){
        HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		String powerId = request.getParameter("id");
		String userId = request.getParameter("userId");

		User user = userService.getUser(Integer.parseInt(userId));
		Power power = powerService.getPower(Integer.parseInt(powerId));
		power.addUser(user);
		boolean a = userService.updateUser(user);
		boolean b = powerService.updatePower(power);

		String tag = null;
		if (a==true && b==true)
			tag = "success";
		else
			tag = "failed";

		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        response.setContentType("json/javascript;charset=utf-8");
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除某个用户的某一个权限
     * @return
     */
    public String delOrder(){
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String orderId = request.getParameter("orderId");

		User user = userService.getUser(Integer.parseInt(userId));
		Power power = powerService.getPower(Integer.parseInt(orderId));
		power.deleteUser(user);
		boolean a = userService.updateUser(user);
		boolean b = powerService.updatePower(power);
		String tag = null;
		if (a==true && b==true)
			tag = "success";
		else
			tag = "failed";

		HashMap<String, String> map = new HashMap<String, String>();
        map.put("state", tag);
        JSONObject json = JSONObject.fromObject(map);
        response.setContentType("json/javascript;charset=utf-8");
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
