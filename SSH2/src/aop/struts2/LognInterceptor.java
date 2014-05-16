package aop.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LognInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init() {
		// TODO Auto-generated method stub
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
        ActionContext invocationContext = invocation.getInvocationContext();
//        String actionName = invocationContext.getName();
        Map session = (Map) invocationContext.get(ServletActionContext.SESSION);
        if (session == null){
            //还没有登陆，放行，转到登陆action
            return invocation.invoke();
        }
        else {
            String userName = (String) session.get("userName");
            if (userName==null)
                return invocation.invoke();  //也是没有登陆，转到登陆操作
            else {
                //用户已经登陆，返回页面
                HashMap<String,String> map = new HashMap<String, String>();
                JSONObject json = JSONObject.fromObject(map);
                map.put("state","success");
                HttpServletResponse response = (HttpServletResponse) invocationContext.get(ServletActionContext.HTTP_RESPONSE);
                response.getWriter().println(json);
                return null;
            }
        }
    }
}
