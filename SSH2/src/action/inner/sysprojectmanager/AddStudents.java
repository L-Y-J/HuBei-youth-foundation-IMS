package action.inner.sysprojectmanager;

import bean.Students;
import bean.Subsidize;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.StudentsService;
import service.SubsidizeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yongjie on 14-6-1.
 */

@Controller
@Scope("prototype")
public class AddStudents extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	StudentsService studentsService;
	@Resource
	SubsidizeService subsidizeService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String studentName;
	private String school;
	private String subsidize;
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

	public String getStudentDate() {
		return studentDate;
	}

	public void setStudentDate(String studentDate) {
		this.studentDate = studentDate;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSubsidize() {
		return subsidize;
	}

	public void setSubsidize(String subsidize) {
		this.subsidize = subsidize;
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	/**
	 * 添加学生
	 * @return
	 */
	public String AddSubsidizeStudents(){
		response.setContentType("json/javascript;charset=utf-8");
		boolean tag = false;

		Students students = new Students();
		students.setStudentName(studentName);
		students.setSchool(school);
		students.setRecordDate(convertDate());
		students.setConnectStudent(connect);

		List list = subsidizeService.queryByName(subsidize);
		if (list.size()!=0){
			Subsidize s = (Subsidize) list.get(0);
			students.getSubsidize().add(s);
			tag = studentsService.addStudent(students);
		}

		String res = "failed";
		if (tag == true)
			res = "success";
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state",res);
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
