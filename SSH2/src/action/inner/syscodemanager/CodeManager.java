package action.inner.syscodemanager;

import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yongjie on 14-5-9.
 */
@Controller @Scope("prototype")
public class CodeManager extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	private HttpServletResponse response;
	private HttpServletRequest request;
	private String departmentId;
	private String number;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 查询所有省级，市级，县级部门，级别使用number:one two three区分
	 * @return
	 */
	public String SelectDepartmentOne(){
		response.setContentType("json/javascript;charset=utf-8");
		HashMap<String,String> map1 = new HashMap<String, String>();
		HashMap<String,String> map2 = new HashMap<String, String>();
		map1.put("id","1");
		map1.put("name","人事部");
		map2.put("id","2");
		map2.put("name","后勤部");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map1);
		jsonArray.add(map2);
		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     * 查询某个部门的所有岗位，部门ID为departmentId
	 * return
	 */
	public String SelectDepartmentJob(){
		response.setContentType("json/javascript;charset=utf-8");

		HashMap<String,String> map1 = new HashMap<String, String>();
		HashMap<String,String> map2 = new HashMap<String, String>();
		map1.put("id","1");
		map1.put("name","秘书");
		map2.put("id","2");
		map2.put("name","经理");

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map1);
		jsonArray.add(map2);
		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
