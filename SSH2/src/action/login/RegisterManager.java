package action.login;

import bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by yongjie on 14-5-23.
 */

@Controller
@Scope("prototype")
public class RegisterManager extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	UserService userService;

	private HttpServletRequest request;
	private HttpServletResponse response;

	private String userName;
	private String name;
	private String innerId;
	private String leval;
	private String password;
	private String passwordAgain;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
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

	public String getLeval() {
		return leval;
	}

	public void setLeval(String leval) {
		this.leval = leval;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}


	/**
	 * 注册操作
	 */
	public String RegisterJump(){
		if (userName==null || userName.equals(""))
			return "register";
		if (name==null || name.equals(""))
			return "register";
		if (innerId==null || innerId.equals(""))
			return "register";
		if (leval==null || leval.equals(""))
			return "register";
		if (password==null || password.equals(""))
			return "register";
		if (!password.equals(passwordAgain))
			return "register";

		User user = new User();
		user.setUserName(userName);
		user.setName(name);
		user.setInnerId(innerId);
		user.setLeval(leval);
		user.setPassword(password);
		boolean a = userService.addUser(user);
		if (!a)
			return "register";
		userService.updateUser(user);
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		session.put("userName",userName);
		return "index";
	}
}
