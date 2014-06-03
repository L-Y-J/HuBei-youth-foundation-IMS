package action.inner.sysprojectmanager;

import bean.SubsidizeSchool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.SubsidizeSchoolService;
import sun.management.resources.agent_es;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-6-3.
 */

@Controller
@Scope("prototype")
public class FindSchools extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	SubsidizeSchoolService subsidizeSchoolService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String startTime;
	private String endTime;
	private String area;
	private String oldSchoolName;
	private String newSchoolName;
	private String subsidizeName;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOldSchoolName() {
		return oldSchoolName;
	}

	public void setOldSchoolName(String oldSchoolName) {
		this.oldSchoolName = oldSchoolName;
	}

	public String getNewSchoolName() {
		return newSchoolName;
	}

	public void setNewSchoolName(String newSchoolName) {
		this.newSchoolName = newSchoolName;
	}

	public String getSubsidizeName() {
		return subsidizeName;
	}

	public void setSubsidizeName(String subsidizeName) {
		this.subsidizeName = subsidizeName;
	}

	public String FindSchools(){
		Set set1 = findSchoolByDate();
		System.out.println(area);
		System.out.println(oldSchoolName);
		System.out.println(newSchoolName);
		System.out.println(subsidizeName);

		return null;
	}

	Set findSchoolByDate(){
		if (startTime.length()==0 || endTime.length()==0)
			return new HashSet();
		Date d1 = convertDate(startTime);
		Date d2 = convertDate(endTime);
		if (d1==null || d2==null)
			return new HashSet();
		List list = subsidizeSchoolService.queryByDate(d1, d2);
		if (list==null)
			return new HashSet();
		return new HashSet(list);
	}

	Set findSchoolByArea(){
		List schools = subsidizeSchoolService.getSubsidizeSchools();
		Set set = new HashSet();
		for (Object o : schools){
			SubsidizeSchool school = (SubsidizeSchool) o;
			if (school.getProvince().equals(area))
				set.add(school);
			else if (school.getQu().equals(area))
				set.add(school);
			else if (school.getXian().equals(area))
				set.add(school);
			else if (school.getXiang().equals(area))
				set.add(school);
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

	private Date convertDate(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
