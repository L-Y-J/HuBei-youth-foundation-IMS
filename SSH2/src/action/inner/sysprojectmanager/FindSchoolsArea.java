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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-6-4.
 */

@Controller
@Scope("prototype")
public class FindSchoolsArea extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	private SubsidizeSchoolService subsidizeSchoolService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String startTime;
	private String endTime;

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

	public String FindAllSchoolArea(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		List schools = subsidizeSchoolService.queryByDate(convertDate(startTime),convertDate(endTime));
		List qu = subsidizeSchoolService.queryDifferintArea("qu");
		List xian = subsidizeSchoolService.queryDifferintArea("xian");
		List xiang = subsidizeSchoolService.queryDifferintArea("xiang");
		List cun = subsidizeSchoolService.queryDifferintArea("cun");

		for(Object o : qu){
			String s = (String) o;    //每一个区
			int []num = countQuNum(schools,s);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("area",s);
			map.put("num0",Integer.toString(num[0]));
			map.put("num1",Integer.toString(num[1]));
			map.put("num2",Integer.toString(num[2]));
			map.put("num3",Integer.toString(num[3]));
			map.put("num4",Integer.toString(num[4]));
			jsonArray.add(map);
		}
		for(Object o : xian){
			String s = (String) o;
			int []num = countQuNum(schools,s);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("area",s);
			map.put("num0",Integer.toString(num[0]));
			map.put("num1",Integer.toString(num[1]));
			map.put("num2",Integer.toString(num[2]));
			map.put("num3",Integer.toString(num[3]));
			map.put("num4",Integer.toString(num[4]));
			jsonArray.add(map);
		}
		for(Object o : xiang){
			String s = (String) o;
			int []num = countQuNum(schools,s);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("area",s);
			map.put("num0",Integer.toString(num[0]));
			map.put("num1",Integer.toString(num[1]));
			map.put("num2",Integer.toString(num[2]));
			map.put("num3",Integer.toString(num[3]));
			map.put("num4",Integer.toString(num[4]));
			jsonArray.add(map);
		}
		for(Object o : cun){
			String s = (String) o;
			int []num = countQuNum(schools,s);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("area",s);
			map.put("num0",Integer.toString(num[0]));
			map.put("num1",Integer.toString(num[1]));
			map.put("num2",Integer.toString(num[2]));
			map.put("num3",Integer.toString(num[3]));
			map.put("num4",Integer.toString(num[4]));
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String FindSubsidizeSchoolArea(){
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		List schools = subsidizeSchoolService.queryByDate(convertDate(startTime),convertDate(endTime));

		return null;
	}

	private int[] countQuNum(List l,String qu){
		int []num = new int[5];
		for (int i = 0; i <5 ; i++) {
			num[i]=0;
		}

		for (Object o : l){
			SubsidizeSchool s = (SubsidizeSchool) o;
			if (s.getQu()!=null && s.getQu().equals(qu)){
				num[0]++;
				if (s.getSubsidize().size()>0){
					num[1]++;
					num[2]++;
					num[3]++;
					num[4]++;
				}
			}
		}
		return num;
	}

	private int[] countXianNum(List l,String xian){
		int []num = new int[5];
		for (int i = 0; i <5 ; i++) {
			num[i]=0;
		}

		for (Object o : l){
			SubsidizeSchool s = (SubsidizeSchool) o;
			if (s.getQu()!=null && s.getXian().equals(xian)){
				num[0]++;
				if (s.getSubsidize().size()>0){
					num[1]++;
					num[2]++;
					num[3]++;
					num[4]++;
				}
			}
		}
		return num;
	}

	private int[] countXiangNum(List l,String xiang){
		int []num = new int[5];
		for (int i = 0; i <5 ; i++) {
			num[i]=0;
		}

		for (Object o : l){
			SubsidizeSchool s = (SubsidizeSchool) o;
			if (s.getQu()!=null && s.getXiang().equals(xiang)){
				num[0]++;
				if (s.getSubsidize().size()>0){
					num[1]++;
					num[2]++;
					num[3]++;
					num[4]++;
				}
			}
		}
		return num;
	}

	private int[] countCunNum(List l,String cun){
		int []num = new int[5];
		for (int i = 0; i <5 ; i++) {
			num[i]=0;
		}

		for (Object o : l){
			SubsidizeSchool s = (SubsidizeSchool) o;
			if (s.getQu()!=null && s.getCun().equals(cun)){
				num[0]++;
				if (s.getSubsidize().size()>0){
					num[1]++;
					num[2]++;
					num[3]++;
					num[4]++;
				}
			}
		}
		return num;
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



















