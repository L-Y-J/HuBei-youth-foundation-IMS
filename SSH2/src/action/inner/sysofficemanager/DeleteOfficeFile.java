package action.inner.sysofficemanager;

import bean.User;
import bean.UserFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UserFileService;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by yongjie on 14-5-27.
 */

@Controller
@Scope("prototype")
public class DeleteOfficeFile extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	UserService userService;
	@Resource
	UserFileService userFileService;

	private HttpServletResponse response;
	private HttpServletRequest request;

	private String deleteReceiveId;
	private String deleteSendId;
	private String deleteRubbishId;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		response = httpServletResponse;
	}

	public String getDeleteReceiveId() {
		return deleteReceiveId;
	}

	public void setDeleteReceiveId(String deleteReceiveId) {
		this.deleteReceiveId = deleteReceiveId;
	}

	public String getDeleteSendId() {
		return deleteSendId;
	}

	public void setDeleteSendId(String deleteSendId) {
		this.deleteSendId = deleteSendId;
	}

	public String getDeleteRubbishId() {
		return deleteRubbishId;
	}

	public void setDeleteRubbishId(String deleteRubbishId) {
		this.deleteRubbishId = deleteRubbishId;
	}

	/**
	 * 查询用户删除的邮件
	 */
	public String UserDeleteFile(){
		response.setContentType("json/javascript;charset=utf-8");

		String userName	= null;
		try {
			ActionContext context = ServletActionContext.getContext();
			userName = (String) context.getSession().get("userName");
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}

		User user = userService.queryUserByName(userName);
		Set sendFiles = user.getSendFile();
		Set reciveFiles = user.getReciveFile();
		JSONArray jsonArray = new JSONArray();
		for (Object o : sendFiles){
			UserFile f = (UserFile) o;
			if (f.getSendDelete()==1){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("haoma",f.getFileId().toString());
				map.put("zhuangtai",f.getIsRead().toString());
				String fujian="0";
				if (f.getUploadFileId()!=null)
					fujian="1";
				map.put("fujian",fujian);
				map.put("fajianren",userName);
				map.put("zhuti",f.getUserFileName());
				map.put("fajianshijian",f.getDate().toString());
				map.put("neirong",f.getContent());
				jsonArray.add(map);
			}
		}

		for (Object o : reciveFiles){
			UserFile f = (UserFile) o;
			if (f.getReceiveDelete()==1){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("haoma",f.getFileId().toString());
				map.put("zhuangtai",f.getIsRead().toString());
				String fujian="0";
				if (f.getUploadFileId()!=null)
					fujian="1";
				map.put("fujian",fujian);
				User tempUser = userService.getUser(f.getFileFrom());
				map.put("fajianren",tempUser.getUserName());
				map.put("zhuti",f.getUserFileName());
				map.put("fajianshijian",f.getDate().toString());
				map.put("neirong",f.getContent());
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


	/**
	 * 删除接收的文件
	 */
	public String DelReceiveFile(){
		String[] split = deleteReceiveId.split("\\|");
		for (String o : split){
			UserFile file = userFileService.getUserFile(Integer.parseInt(o));
			file.setReceiveDelete(1);
			userFileService.updateUserFile(file);
		}
		return null;
	}


	/**
	 * 删除发送的文件
	 */
	public String DelSendFile(){
		String[] split = deleteReceiveId.split("\\|");
		for (String o : split){
			UserFile file = userFileService.getUserFile(Integer.parseInt(o));
			file.setSendDelete(1);
			userFileService.updateUserFile(file);
		}
		return null;
	}


	/**
	 * 删除垃圾箱
	 */
	public String DelRubbishFile(){
		String userName	= null;
		try {
			ActionContext context = ServletActionContext.getContext();
			userName = (String) context.getSession().get("userName");
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}

		User user = userService.queryUserByName(userName);
		Set set1 = user.getReciveFile();
		Set set2 = user.getSendFile();

		String[] split = deleteRubbishId.split("\\|");
		for (String s : split){
			UserFile file = userFileService.getUserFile(Integer.parseInt(s));
			if (set1.contains(file))
				file.setReceiveDelete(2);
			if (set2.contains(file))
				file.setSendDelete(2);
			userFileService.updateUserFile(file);
		}
		return null;
	}



}
