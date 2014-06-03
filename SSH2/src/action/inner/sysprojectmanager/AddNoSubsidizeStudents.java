package action.inner.sysprojectmanager;

import bean.Students;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.StudentsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yongjie on 14-6-2.
 */

@Controller
@Scope("prototype")
public class AddNoSubsidizeStudents extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	StudentsService studentsService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String studentName;
	private String school;
	private String studentDate;
	private String connect;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudentDate() {
		return studentDate;
	}

	public void setStudentDate(String studentDate) {
		this.studentDate = studentDate;
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}



	public String AddStudents(){
		response.setContentType("json/javascript;charset=utf-8");
		Students students = new Students();
		students.setStudentName(studentName);
		students.setSchool(school);
		students.setRecordDate(convertDate());
		students.setConnectStudent(connect);
		boolean a = studentsService.addStudent(students);
		String tag = "failed";
		if (a)
			tag = "success";

		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state",tag);
		JSONObject jsonObject = JSONObject.fromObject(map);
		try {
			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Date convertDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(this.studentDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
