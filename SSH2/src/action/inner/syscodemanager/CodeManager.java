package action.inner.syscodemanager;

import bean.Department;
import bean.Job;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.DepartmentService;
import sun.tools.jar.resources.jar_it;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-5-9.
 */
@Controller @Scope("prototype")
public class CodeManager extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	DepartmentService departmentService;

	private HttpServletResponse response;
	private HttpServletRequest request;
	private String departmentId;
	private String departName;
	private String data;
	private String jobId;
	private String jobName;

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

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 查询所有省级，市级，县级部门
	 * @return
	 */
	public String SelectDepartmentOne(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		List list = departmentService.getDepartments();
		if (list!=null){
			for (Object o : list){
				Department department = (Department) o;
				if (department.getLeval().equals(this.data)){
					HashMap<String,String> map = new HashMap<String, String>();
					map.put("id",department.getId().toString());
					map.put("name",department.getDepartmentName());
					jsonArray.add(map);
				}
			}
		}

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

		JSONArray jsonArray = new JSONArray();
		Department department = departmentService.getDepartment(Integer.parseInt(this.departmentId));
		Set jobs = department.getJob();
		if (jobs!=null){
			for (Object o : jobs){
				Job j = (Job) o;
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("id",j.getId().toString());
				map.put("name",j.getJobName());
				jsonArray.add(map);
			}
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 新增一个部门,departmentName data(表示级别)
	 * return
	 */
	public String AddDepartment(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray json = new JSONArray();
		HashMap<String,String> map = new HashMap<String, String>();

		Department department = new Department();
		department.setDepartmentName(this.departName);
		department.setLeval(this.data);
		boolean b = departmentService.addDepartment(department);
		if (b){
			map.put("state","success");
		}
		else{
			map.put("state","failed");
		}
		json.add(map);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 删除部门
	 * rturn
	 */
	public String DelDepartment(){
		response.setContentType("json/javascript;charset=utf-8");
		String tag = "failed";

		Department department = departmentService.getDepartment(Integer.parseInt(this.departmentId));
		if (department!=null){
			boolean a = departmentService.deleteDepartment(department);
			if (a)
				tag = "success";
		}

		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state",tag);
		JSONObject json = JSONObject.fromObject(map);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加职位
	 * @return
	 */
	public String AddJob(){
		response.setContentType("json/javascript;charset=utf-8");
		String tag = "failed";

		Department department = departmentService.getDepartment(Integer.parseInt(this.departmentId));
		if (department!=null){
			Job job = new Job();
			job.setJobName(this.jobName);
			department.getJob().add(job);
			boolean a = departmentService.updateDepartment(department);
			if (a)
				tag = "success";
		}

		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state",tag);
		JSONObject json = JSONObject.fromObject(map);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String DelJob(){
		response.setContentType("json/javascript;charset=utf-8");
		String tag = "failed";



		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state",tag);
		JSONObject json = JSONObject.fromObject(map);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
