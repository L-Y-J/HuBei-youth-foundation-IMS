package action.jump;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by yongjie on 14-5-8.
 */
@Controller
@Scope("prototype")
public class JumpToInner extends ActionSupport {

	/**
     *跳转到内部管理系统
	 */
	public String JumpToInnerPower(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		if (session==null){
			System.out.println("Session为空");
			return "index";
		}
		else{
			String userName = (String) session.get("userName");
			System.out.println(userName);
			if (userName==null){
				System.out.println("用户名称为空，需要重新登陆");
				return "index";
			}
			else {
				System.out.println("用户已经登陆，可以跳转");
				return "innerpower";
			}
		}
	}

	public String JumpToSysCode(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		if (session==null){
			System.out.println("Session为空");
			return "index";
		}
		else{
			String userName = (String) session.get("userName");
			System.out.println(userName);
			if (userName==null){
				System.out.println("用户名称为空，需要重新登陆");
				return "index";
			}
			else {
				System.out.println("用户已经登陆，可以跳转");
				return "syscode";
			}
		}
	}

	public String JumpToOffice(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		if (session==null){
			System.out.println("Session为空");
			return "index";
		}
		else{
			String userName = (String) session.get("userName");
			System.out.println(userName);
			if (userName==null){
				System.out.println("用户名称为空，需要重新登陆");
				return "index";
			}
			else {
				System.out.println("用户已经登陆，可以跳转");
				return "office";
			}
		}
	}

	public String JumpToProject(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		if (session==null){
			System.out.println("Session为空");
			return "index";
		}
		else{
			String userName = (String) session.get("userName");
			System.out.println(userName);
			if (userName==null){
				System.out.println("用户名称为空，需要重新登陆");
				return "index";
			}
			else {
				System.out.println("用户已经登陆，可以跳转");
				return "project";
			}
		}
	}

	public String JumpToSubsidize(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		if (session==null){
			System.out.println("Session为空");
			return "index";
		}
		else{
			String userName = (String) session.get("userName");
			System.out.println(userName);
			if (userName==null){
				System.out.println("用户名称为空，需要重新登陆");
				return "index";
			}
			else {
				System.out.println("用户已经登陆，可以跳转");
				return "subsidize";
			}
		}
	}

	public String JumpToOuter(){
		return "outer";
	}


	//跳转到首页
	public String JumpToIndex(){
		ActionContext context = ServletActionContext.getContext();
		Map session = context.getSession();
		session.remove("userName");
		return "index";
	}

}
