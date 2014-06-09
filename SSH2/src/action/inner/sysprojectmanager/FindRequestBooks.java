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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yongjie on 14-6-8.
 */

@Controller
@Scope("prototype")
public class FindRequestBooks extends ActionSupport implements ServletRequestAware,ServletResponseAware {

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

	public String RequestBooks(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		List schools = schoolService.getSubsidizeSchools();
		for (Object o : schools){
			SubsidizeSchool school = (SubsidizeSchool) o;
			if (school.getQu().equals(qu)){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("id",school.getSchoolId().toString());
				map.put("area",qu);
				map.put("date",school.getRecordDate()!=null ? school.getRecordDate().toString():"");
				jsonArray.add(map);
			}
			if (school.getXian().equals(xian)){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("id",school.getSchoolId().toString());
				map.put("area",xian);
				map.put("date",school.getRecordDate()!=null ? school.getRecordDate().toString():"");
			}
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
