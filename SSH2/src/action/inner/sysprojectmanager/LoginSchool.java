package action.inner.sysprojectmanager;

import bean.Job;
import bean.Subsidize;
import bean.SubsidizeSchool;
import bean.Writer;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.JobService;
import service.SubsidizeSchoolService;
import service.SubsidizeService;
import service.WriterService;
import sun.tools.jar.resources.jar_sv;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yongjie on 14-6-7.
 */

@Controller
@Scope("prototype")
public class LoginSchool extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	@Resource
	SubsidizeSchoolService subsidizeSchoolService;
	@Resource
	SubsidizeService subsidizeService;
	@Resource
	WriterService writerService;
	@Resource
	JobService jobService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String writeName;
	private String writeJob;
	private String writePhone;
	private String writeFax;
	private String writeMail;
	private String writeCellPhone;

	private String SubsidizeName, SubsidizeDate, schoolName, subsidizeType, schoolCompleteDate, schoolArea,
			presidentName, schoolMail, schoolCellPhone, schoolPhone, schoolRemrak, schoolIsMerge,schoolQu,schoolXian;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public void setWriteName(String writeName) {
		this.writeName = writeName;
	}

	public void setWriteJob(String writeJob) {
		this.writeJob = writeJob;
	}

	public void setWritePhone(String writePhone) {
		this.writePhone = writePhone;
	}

	public void setWriteFax(String writeFax) {
		this.writeFax = writeFax;
	}

	public void setWriteMail(String writeMail) {
		this.writeMail = writeMail;
	}

	public void setWriteCellPhone(String writeCellPhone) {
		this.writeCellPhone = writeCellPhone;
	}

	public void setSubsidizeName(String subsidizeName) {
		SubsidizeName = subsidizeName;
	}

	public void setSubsidizeDate(String subsidizeDate) {
		SubsidizeDate = subsidizeDate;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void setSubsidizeType(String subsidizeType) {
		this.subsidizeType = subsidizeType;
	}

	public void setSchoolCompleteDate(String schoolCompleteDate) {
		this.schoolCompleteDate = schoolCompleteDate;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public void setPresidentName(String presidentName) {
		this.presidentName = presidentName;
	}

	public void setSchoolMail(String schoolMail) {
		this.schoolMail = schoolMail;
	}

	public void setSchoolCellPhone(String schoolCellPhone) {
		this.schoolCellPhone = schoolCellPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public void setSchoolRemrak(String schoolRemrak) {
		this.schoolRemrak = schoolRemrak;
	}

	public void setSchoolIsMerge(String schoolIsMerge) {
		this.schoolIsMerge = schoolIsMerge;
	}

	public void setSchoolQu(String schoolQu) {
		this.schoolQu = schoolQu;
	}

	public void setSchoolXian(String schoolXian) {
		this.schoolXian = schoolXian;
	}

	public String FindSubsidize() {
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();
		List subsidizes = subsidizeService.getSubsidizes();
		for (Object o : subsidizes) {
			Subsidize s = (Subsidize) o;
			HashMap<String, String> map = new HashMap<String, String>();
			if (s.getSubsidizer() != null) {
				map.put("name", s.getSubsidizer());
				jsonArray.add(map);
			}
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String FindAreaQu() {
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();

		List qu = subsidizeSchoolService.queryDifferintArea("qu");

		for (Object o : qu) {
			String s = (String) o;
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", s);
			jsonArray.add(map);
		}
		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String FindAreaXian() {
		response.setContentType("json/javascript;charset=utf-8");
		JSONArray jsonArray = new JSONArray();
		List xian = subsidizeSchoolService.queryDifferintArea("xian");
		for (Object o : xian) {
			String s = (String) o;
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", s);
			jsonArray.add(map);
		}

		try {
			response.getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String SaveWriterAndSchoolInfo() {
		Writer writer = new Writer();
		writer.setWriterName(writeName);
		Job job = jobService.queryJobByName(writeJob);
		writer.setJobId(job != null ? job.getId() : null);
		writer.setPhone(writePhone);
		writer.setFax(writeFax);
		writer.setEmail(writeMail);
		writer.setCellPhone(writeCellPhone);
		boolean a = writerService.addWriter(writer);

		List list = subsidizeService.queryByName(SubsidizeName);
		Subsidize s = list != null ? (Subsidize) list.get(0) : null;
		if (s!=null)
			s.setSubsidizeDate(convertDate(SubsidizeDate));

		SubsidizeSchool school = new SubsidizeSchool();
		school.setSchoolName(schoolName);
		school.setCompleteDate(convertDate(schoolCompleteDate));
		school.setPresidentName1(presidentName);
		school.setEmail1(schoolMail);
		school.setCellPhone1(schoolCellPhone);
		school.setHomePhone(schoolPhone);
		school.setIsMerge(schoolIsMerge.equals("是") ? 1:0);
		school.setQu(schoolQu);
		school.setXian(schoolXian);
		if (s!=null)
			school.getSubsidize().add(s);
		boolean b = subsidizeSchoolService.addSubsidizeSchool(school);

		String tag = "failed";
		if (a==true && b==true)
			tag = "success";

		HashMap<String,String> map = new HashMap<String, String>();
		map.put("state", tag);
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.setContentType("json/javascript;charset=utf-8");
		try {
			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
