package aop.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

/**
 * Created by yongjie on 14-5-8.
 */
public class JumpInterceptor implements Interceptor {
	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext invocationContext = actionInvocation.getInvocationContext();
		Map session = (Map) invocationContext.get(ServletActionContext.SESSION);
		if (session==null){
			System.out.println("用户还没有登陆");
			return "index";
		}
		else{
			String userName = (String) session.get("userName");
			if (userName==null){
				System.out.println("用户session为空");
				return "index";
			}
			else{
				System.out.println("用户已经登陆，允许跳转");
				return actionInvocation.invoke();
			}
		}
	}
}
