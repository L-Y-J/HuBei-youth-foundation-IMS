package action.inner.sysprojectmanager;

import bean.Subsidize;
import bean.SubsidizeSchool;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.SubsidizeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yongjie on 14-6-10.
 */
@Controller
@Scope("prototype")
public class FindSubsidizeState  extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	SubsidizeService subsidizeService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	String start,end,subsidizeName;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setSubsidizeName(String subsidizeName) {
		this.subsidizeName = subsidizeName;
	}

	public String SubSidizeState(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		Set set1 = FindByDate();
		Set set2 = FindByName();

		getSet(set1, set2);

		for (Object o : set1) {
			Subsidize subsidize = (Subsidize) o;
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("name",subsidize.getSubsidizer());
			map.put("place",subsidize.getSubsidizerPlace());
			Set school = subsidize.getSchool();
			List<SubsidizeSchool> schoolList = null;
			if (school!=null && school.size()!=0)
				schoolList = new ArrayList<SubsidizeSchool>(school);
			map.put("schoolName",schoolList!=null ? schoolList.get(0).getSchoolName():"");
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Set FindByDate(){
		Set set = new HashSet();
		if (start.length()==0 || end.length()==0)
			return set;

		List list = subsidizeService.queryByDate(convertDate(start), convertDate(end));
		if (list!=null && list.size()!=0)
			set.addAll(list);
		return set;
	}

	private Set FindByName(){
		Set set = new HashSet();
		if (subsidizeName.length()==0)
			return set;

		List list = subsidizeService.queryByName(subsidizeName);
		if (list!=null && list.size()!=0)
			set.addAll(list);
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
