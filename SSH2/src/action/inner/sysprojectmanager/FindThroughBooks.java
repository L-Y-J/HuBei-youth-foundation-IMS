package action.inner.sysprojectmanager;

import bean.SubsidizeSchool;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.SubsidizeSchoolService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by yongjie on 14-6-10.
 */

@Controller
@Scope("prototype")
public class FindThroughBooks extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	SubsidizeSchoolService schoolService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String qu,xian;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public String ThroughBooks(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		List schools = schoolService.getSubsidizeSchools();
		Set<SubsidizeSchool> set = new HashSet<SubsidizeSchool>();
		if (schools!=null){
			for (Object o : schools) {
				SubsidizeSchool school = (SubsidizeSchool) o;
				if (school.getQu()!=null && school.getQu().equals(qu))
					set.add(school);
				if (school.getXian()!=null && school.getXian().equals(xian))
					set.add(school);
			}

		}

		for (SubsidizeSchool o : set) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("id",o.getSchoolId().toString());
			map.put("area",o.getQu()!=null ? o.getQu():(o.getXian()!=null ? o.getXian():""));
			map.put("date",o.getRecordDate()!=null ? o.getRecordDate().toString():"");
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
