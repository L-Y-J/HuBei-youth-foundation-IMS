package action.login;

import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller @Scope("prototype")
public class LoginManager extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    private static final long serialVersionUID = 1L;

	@Resource
	UserService userService;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String userName;
    private String password;

    public void setServletResponse(HttpServletResponse res) {
        // TODO Auto-generated method stub
        this.response = res;
    }
    public void setServletRequest(HttpServletRequest req) {
        // TODO Auto-generated method stub
        this.request = req;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户提交登陆请求，检查数据库用户是否注册，返回登陆结果
     * @return
     */
    public String LoginSubmit() {
		String logTag = "failed";
		List list = userService.queryNameAndPassword(this.userName, this.password);
		if (list.size()!=0){
			logTag = "success";
			ActionContext context = ServletActionContext.getContext();
			Map session = context.getSession();
			session.put("userName",userName);
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("state", logTag);
        JSONObject json = JSONObject.fromObject(map);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 用户退出登陆，清除session
	 * return
	 */
	public String ClearSession(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		session.remove("userName");

		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state","success");
		JSONObject json = JSONObject.fromObject(map);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检查session是否存在，验证用户的登陆状态
	 */
	 public String CheckSession(){
		 ActionContext context = ServletActionContext.getContext();
		 Map session = context.getSession();
		 String name = (String) session.get("userName");
		 HashMap<String,String> map = new HashMap<String, String>();
		 if (name==null){
			 map.put("state","failed");
		 }
		 else{
			 map.put("state","success");
			 map.put("name",name);
		 }
		 JSONObject jsonObject = JSONObject.fromObject(map);
		 try {
			 response.getWriter().println(jsonObject);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return null;
	}

	/**
	 * 登陆并且跳转
	 * @return
	 */
	public String LoginJump(){
		List list = userService.queryNameAndPassword(this.userName, this.password);
		if (list.size()!=0){
			ActionContext context = ServletActionContext.getContext();
			Map session = context.getSession();
			session.put("userName",userName);
			return "index";
		}
		return "login";
	}
}















