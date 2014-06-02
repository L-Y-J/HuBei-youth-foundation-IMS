package action.inner.sysprojectmanager;

import bean.Students;
import bean.Subsidize;
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
 * Created by yongjie on 14-5-29.
 */

@Controller
@Scope("prototype")
public class FindStudents extends ActionSupport implements ServletRequestAware,ServletResponseAware {

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
	private String subsidizeName;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
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

	public String getSubsidizeName() {
		return subsidizeName;
	}

	public void setSubsidizeName(String subsidizeName) {
		this.subsidizeName = subsidizeName;
	}

	/**
	 * 通过时间，学校，学生编号，姓名，资助方名称等查找学生
	 * @return
	 */
	public String FindStudentBySomeThings(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();
		Set set1=new HashSet(),set2=new HashSet(),set3=new HashSet(),set4=new HashSet(),set5=new HashSet();
		if (this.startTime!=null && startTime.length()>0 && this.endTime!=null && this.endTime.length()>0)
			set1 = findStudentsByTime();
		if (this.school!=null && this.school.length()>0)
			set2 = findStudentsBySchool();
		if (this.studentNumber!=null && this.studentNumber.length()>0)
			set3 = findStudentsByNumber();
		if (this.subsidizeName!=null && this.subsidizeName.length()>0)
			set4 = findStudentsBysubsidizeName();
		if (this.studentName!=null && this.studentName.length()>0)
			set5 = findStudentsByName();

		getSet(set1,set2);
		getSet(set1,set3);
		getSet(set1,set4);
		getSet(set1,set5);

		for (Object o : set1){
			Students s = (Students) o;
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id",s.getStudentId().toString());
			map.put("name",s.getStudentName());
			map.put("school",s.getSchool());
			List l = new ArrayList(s.getSubsidize());
			Subsidize sub = (Subsidize) l.get(0);
			map.put("subidizeName",sub.getSubsidizer());
			map.put("subidizeDate",sub.getSubsidizeDate().toString());
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

	private Set findStudentsByTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date endDate;
		try {
			startDate = dateFormat.parse(this.startTime);
			endDate = dateFormat.parse(this.endTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		List list = subsidizeService.queryByDate(startDate, endDate);
		if (list==null)
			return null;

		Set set = new HashSet();
		for (Object o : list){
			Set students = ((Subsidize) o).getStudents();
			set.addAll(students);
		}
		return set;
	}

	private Set findStudentsBySchool(){
		List list = studentsService.queryStudentBySchool(this.school);
		return new HashSet(list);
	}

	private Set findStudentsByName(){
		List list = studentsService.queryStudentByName(this.studentName);
		Set set = new HashSet();
		for (Object o : list){
			Students s = (Students) o;
			if (s.getSubsidize().size()>0)
				set.add(s);
		}
		return set;
	}

	private Set findStudentsByNumber(){
		Students student = studentsService.getStudent(Integer.parseInt(this.studentNumber));
		Set set = new HashSet();
		if (student.getSubsidize().size()>0)
			set.add(student);
		return set;
	}

	private Set findStudentsBysubsidizeName(){
		List list = subsidizeService.queryByName(this.subsidizeName);
		Set set = new HashSet();
		for (Object o : list){
			Subsidize s = (Subsidize) o;
			set.addAll(s.getStudents());
		}
		return set;
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


















