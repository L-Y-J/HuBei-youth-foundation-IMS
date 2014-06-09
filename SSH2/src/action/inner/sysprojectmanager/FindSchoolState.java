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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yongjie on 14-6-8.
 */

@Controller
@Scope("prototype")
public class FindSchoolState extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	SubsidizeSchoolService subsidizeSchoolService;
	@Resource
	SubsidizeService subsidizeService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	String startTime,endTime,subsidizeName,oldSchoolName,area;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setSubsidizeName(String subsidizeName) {
		this.subsidizeName = subsidizeName;
	}

	public void setOldSchoolName(String oldSchoolName) {
		this.oldSchoolName = oldSchoolName;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String SchoolStates(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		Set set1 = getByDate();
		Set set2 = getBySubsidizeName();
		Set set3 = getByOldSchoolName();
		Set set4 = getSchoolByArea();

		getSet(set1,set2);
		getSet(set1,set3);
		getSet(set1,set4);

		for (Object o : set1){
			SubsidizeSchool school = (SubsidizeSchool) o;
			HashMap<String,String> map = new HashMap<String, String>();
			Set subsidize = school.getSubsidize();
			List<Subsidize> subsidizeList = null;
			if (subsidize!=null && subsidize.size()!=0)
				subsidizeList = new ArrayList<Subsidize>(subsidize);
			map.put("subsidizeName",subsidizeList!=null ? subsidizeList.get(0).getSubsidizer():"");
			map.put("area",school.getQu()!=null?school.getQu():(school.getXian()!=null?school.getXian():""));
			map.put("schoolName",school.getSchoolName());
			map.put("book1","");
			map.put("book2","");
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Set getByDate(){
		Set set = new HashSet();
		if (startTime.length()==0 || endTime.length()==0)
			return set;

		Date d1 = convertDate(startTime);
		Date d2 = convertDate(endTime);

		List list = subsidizeSchoolService.queryByDate(d1,d2);
		if (list!=null)
			set.addAll(list);
		return set;
	}

	private Set getBySubsidizeName(){
		Set set = new HashSet();

		if (subsidizeName.length()==0)
			return set;

		List list = subsidizeService.queryByName(subsidizeName);
		if (list==null)
			return set;
		for (Object o : list){
			Subsidize s = (Subsidize) o;
			if (s.getSchool()!=null)
				set.addAll(s.getSchool());
		}
		return set;
	}

	private Set getByOldSchoolName(){
		Set set = new HashSet();
		if (oldSchoolName.length()==0)
			return set;

		List list = subsidizeSchoolService.getSubsidizeSchools();
		if (list==null)
			return set;
		for (Object o : list){
			SubsidizeSchool s = (SubsidizeSchool) o;
			if (s.getMergeSchool()!=null && s.getMergeSchool().equals(oldSchoolName))
				set.add(s);
		}
		return set;
	}

	private Set getSchoolByArea(){
		Set set = new HashSet();
		if (area.length()==0)
			return set;

		List schools = subsidizeSchoolService.getSubsidizeSchools();
		if (schools==null)
			return set;
		for (Object o : schools){
			SubsidizeSchool s = (SubsidizeSchool) o;
			if (s.getQu()!=null && s.getQu().equals(area))
				set.add(s);
			if (s.getXian()!=null && s.getXian().equals(area))
				set.add(s);
		}
		return set;
	}

	private void getSet(Set set1,Set set2){
		if (set1.size()==0 || set2.size()==0)
			set1.addAll(set2);
		else
			set1.retainAll(set2);
	}

	private Date convertDate(String date){
		if (date.length()==0)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
