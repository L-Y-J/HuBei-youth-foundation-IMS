package action.inner.sysprojectmanager;

import bean.Students;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
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
import java.util.*;

/**
 * Created by yongjie on 14-6-2.
 */

@Controller
@Scope("prototype")
public class FindNoSubsidizeStudents extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	SubsidizeService subsidizeService;
	@Resource
	StudentsService studentsService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String startTime;
	private String endTime;
	private String school;
	private String studentNumber;
	private String studentName;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	/**
	 * 查询未受资助的学生
	 * @return
	 */
	public String FindStudentsNoSubidize(){
		response.setContentType("json/javascript;charset=utf-8");

		Set set1,set2,set3,set4;
		set1 = getStudentsByTime();
		set2 = getStudentsBySchool();
		set3 = getStudentsByNumber();
		set4 = getStudentsByName();

		getSet(set1,set2);
		getSet(set1,set3);
		getSet(set1,set4);

		JSONArray jsonArray  = new JSONArray();
		for (Object o : set1){
			Students s = (Students) o;
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id",s.getStudentId().toString());
			map.put("name",s.getStudentName());
			map.put("school",s.getSchool());
			map.put("date",s.getRecordDate().toString());
			map.put("connect",s.getConnectStudent());
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Set getStudentsByTime(){
		if (startTime.length()==0 || endTime.length()==0)
			return new HashSet();

		List list = studentsService.queryStudentsByTime(convertDate(startTime), convertDate(endTime));
		if (list==null)
			return new HashSet();

		Iterator iterator = list.iterator();
		while (iterator.hasNext()){
			Students s = (Students) iterator.next();
			if (s.getSubsidize().size()>0)
				iterator.remove();
		}
		return new HashSet(list);
	}

	private Set getStudentsBySchool(){
		List list = studentsService.queryStudentBySchool(school);
		if (list==null)
			return new HashSet();

		Iterator iterator = list.iterator();
		while (iterator.hasNext()){
			Students s = (Students) iterator.next();
			if (s.getSubsidize().size()>0)
				iterator.remove();
		}
		return new HashSet(list);
	}

	private Set getStudentsByNumber(){
		if (studentNumber.length()==0)
			return new HashSet();
		Students student = studentsService.getStudent(Integer.parseInt(studentNumber));
		if (student==null || student.getSubsidize().size()>0)
			return new HashSet();
		Set set = new HashSet();
		set.add(student);
		return set;
	}

	private Set getStudentsByName(){
		List list = studentsService.queryStudentByName(studentName);
		if (list==null)
			return new HashSet();

		Iterator iterator = list.iterator();
		while (iterator.hasNext()){
			Students s = (Students) iterator.next();
			if (s.getSubsidize().size()>0)
				iterator.remove();
		}
		return new HashSet(list);
	}

	private Date convertDate(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void getSet(Set set1,Set set2){
		if (set1.size()==0 || set2.size()==0){
			set1.addAll(set2);
		}
		else{
			set1.retainAll(set2);
		}
	}
}
