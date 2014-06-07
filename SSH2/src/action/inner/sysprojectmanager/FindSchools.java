package action.inner.sysprojectmanager;

import bean.Subsidize;
import bean.SubsidizeSchool;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.SubsidizeSchoolService;
import service.SubsidizeService;
import sun.management.resources.agent_es;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yongjie on 14-6-3.
 */

@Controller
@Scope("prototype")
public class FindSchools extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	SubsidizeSchoolService subsidizeSchoolService;
	@Resource
	SubsidizeService subsidizeService;

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
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		Set set1 = findSchoolByDate();
		Set set2 = findSchoolByArea();
		Set set3 = findByOldSchoolName();
		Set set4 = findByNewSchoolName();
		Set set5 = findSchoolBySubidize();

		getSet(set1,set2);
		getSet(set1,set3);
		getSet(set1,set4);
		getSet(set1,set5);

		for (Object o : set1){
			SubsidizeSchool s = (SubsidizeSchool) o;
			HashMap<String,String> map = new HashMap<String, String>();

			String name = "";
			if (s.getSchoolName()!=null)
				name = s.getSchoolName();
			map.put("name",name);

			String subsidizeName = "";
			if (s.getSubsidize().size()>0){
				Object[] objects = s.getSubsidize().toArray();
				subsidizeName = objects[0].toString();
			}
			map.put("subsidizeName",subsidizeName);

			String date = "";
			if (s.getCompleteDate()!=null)
				date = s.getCompleteDate().toString();
			map.put("date",date);

			StringBuffer address = new StringBuffer();
			if (s.getProvince()!=null)
				address.append(s.getProvince()).append(" ");
			if (s.getQu()!=null)
				address.append(s.getQu()).append(" ");
			if (s.getXian()!=null)
				address.append(s.getXian()).append(" ");
			if (s.getXiang()!=null)
				address.append(s.getXiang()).append(" ");
			if (s.getCun()!=null)
				address.append(s.getCun()).append(" ");
			map.put("address",address.toString());

			map.put("mail","暂无");

			String presidentName = null;
			if (s.getPresidentName1()!=null)
				presidentName = s.getPresidentName1();
			else if (s.getPresidentName2()!=null)
				presidentName = s.getPresidentName2();
			else
				presidentName = "";
			map.put("presidentName",presidentName);

			StringBuffer connect = new StringBuffer();
			if (s.getOfficePhoneArea()!=null)
				connect.append(s.getOfficePhoneArea()).append(" ");
			if (s.getOfficePhone()!=null)
				connect.append(s.getOfficePhone());
			map.put("connect",connect.toString());

			map.put("department","暂无");

			String tag = null;
			if (s.getIsMerge()!=null && s.getIsMerge()==1)
				tag = "是";
			else
				tag = "否";
			map.put("merge",tag);

			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

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
			if (school.getProvince()!=null && school.getProvince().equals(area))
				set.add(school);
			else if (school.getQu()!=null && school.getQu().equals(area))
				set.add(school);
			else if (school.getXian()!=null && school.getXian().equals(area))
				set.add(school);
			else if (school.getXiang()!=null && school.getXiang().equals(area))
				set.add(school);
			else if (school.getCun()!=null && school.getCun().equals(area))
				set.add(school);
			else
				continue;
		}
		return set;
	}

	Set findByOldSchoolName(){
		List schools = subsidizeSchoolService.getSubsidizeSchools();
		Set set = new HashSet();
		for (Object o : schools){
			SubsidizeSchool school = (SubsidizeSchool) o;
			if (school.getSchoolName()!=null && school.getSchoolName().equals(oldSchoolName))
				set.add(school);
		}
		return set;
	}

	Set findByNewSchoolName(){
		List schools = subsidizeSchoolService.getSubsidizeSchools();
		Set set = new HashSet();
		for (Object o : schools){
			SubsidizeSchool school = (SubsidizeSchool) o;
			if (school.getMergeSchool()!=null && school.getMergeSchool().equals(newSchoolName))
				set.add(school);
		}
		return set;
	}

	Set findSchoolBySubidize(){
		List list = subsidizeService.getSubsidizes();
		Set set = new HashSet();
		for (Object o : list){
			Subsidize s = (Subsidize) o;
			if (s.getSubsidizer()!=null && s.getSubsidizer().equals(subsidizeName)){
				set.add(s.getSchool());
			}
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
