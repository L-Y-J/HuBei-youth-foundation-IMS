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

/**
 * Created by yongjie on 14-6-1.
 */

@Controller
@Scope("prototype")
public class DeleteStudents extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	StudentsService studentsService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String studentList;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public String getStudentList() {
		return studentList;
	}

	public void setStudentList(String studentList) {
		this.studentList = studentList;
	}


	public String DeleteStudents(){
		String[] split = studentList.split("\\|");
		for (String s : split){
			Students student = studentsService.getStudent(Integer.parseInt(s));
			student.getSubsidize().clear();
			studentsService.deleteStudent(student);
			studentsService.updateStudent(student);
		}

		return null;
	}
}
