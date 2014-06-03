package action.inner.sysprojectmanager;

import bean.Students;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.StudentsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yongjie on 14-6-3.
 */

@Controller
@Scope("prototype")
public class UpdateNoSubsidizeStudents extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	StudentsService studentsService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String id;
	private String name;
	private String school;
	private String date;
	private String connect;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public String UpdateStudents(){
		Students student = studentsService.getStudent(Integer.parseInt(id));
		if (student==null)
			return null;
		student.setStudentName(name);
		student.setSchool(school);
		student.setRecordDate(convertDate());
		student.setConnectStudent(connect);
		studentsService.updateStudent(student);
		return null;
	}

	private Date convertDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(this.date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
