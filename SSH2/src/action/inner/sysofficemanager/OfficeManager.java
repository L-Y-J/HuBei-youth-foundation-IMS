package action.inner.sysofficemanager;

import bean.UploadFile;
import bean.User;
import bean.UserFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UploadFileService;
import service.UserFileService;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by yongjie on 14-5-23.
 */

@Controller
@Scope("prototype")
public class OfficeManager extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	UserService userService;
	@Resource
	UserFileService userFileService;
	@Resource
	UploadFileService uploadFileService;

	private HttpServletResponse response;
	private HttpServletRequest request;
	private String fileId;
	private String receiveUser;   //收件人
	private String fileName;      //文件名
	private String context;       //文件内容
	private File uploadImage;     //附件
	private String uploadImageContentType;  //得到附件的类型
	private String uploadImageFileName;     //得到附件的名称



	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	/**
	 * 文件发送与附件上传
	 */
	public String FileSendUpload(){
		String userName	= null;
		try {
			ActionContext context = ServletActionContext.getContext();
			userName = (String) context.getSession().get("userName");
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}

		User sendUser = null;
		User receiveUSer = null;
		try {
			sendUser = userService.queryUserByName(userName);
			receiveUSer = userService.queryUserByName(receiveUser);
		} catch (Exception e) {
			e.printStackTrace();
			return "errorinfo";
		}

		UserFile userFile = new UserFile();
		userFile.setUserFileName(this.fileName);
		userFile.setDate(new Date());
		userFile.setIsRead(0);
		userFile.setContent(this.context);
		userFile.setFileFrom(sendUser.getId());
		userFile.getFileTo().add(receiveUSer);

		if (uploadImageFileName!=null){
			UploadFile upload = new UploadFile();
			upload.setFileName(uploadImageFileName);
			uploadFileService.addUploadFile(upload);
			uploadFileService.updateUploadFile(upload);
			userFile.setUploadFileId(upload.getId());

			String realpath = ServletActionContext.getServletContext().getRealPath("/");
			File file = new File(realpath);
			if(!file.exists())
				file.mkdirs();
			try {
				FileUtils.copyFile(uploadImage, new File(file, uploadImageFileName));
			} catch (IOException e) {
				e.printStackTrace();
				return "errorinfo";
			}
		}
		userFileService.updateUserFile(userFile);
		return "officemanager";
	}

	/**
	 * 查询用户的收件箱
	 */
	public String UserReciveFile(){
		response.setContentType("json/javascript;charset=utf-8");

		ActionContext context = ServletActionContext.getContext();
		String userName	= (String) context.getSession().get("userName");
		if (userName==null)
			return "login";

		JSONArray jsonArray = new JSONArray();
		User user = userService.queryUserByName(userName);

		if (user!=null){
			Set reciveFile = user.getReciveFile();
			for (Object o : reciveFile){
				UserFile file = (UserFile) o;
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("haoma",file.getFileId().toString());
				map.put("zhuangtai",file.getIsRead().toString());
				map.put("fujian","0");
				String name = userService.getUser(file.getFileFrom()).getUserName();
				map.put("fajianren",name);
				map.put("zhuti",file.getUserFileName());
				map.put("fajianshijian",file.getDate().toString());
				map.put("neirong",file.getContent());
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
	 * 查询用户的发件箱
	 */
	public String UserSendFile(){
		response.setContentType("json/javascript;charset=utf-8");

		ActionContext context = ServletActionContext.getContext();
		String userName	= (String) context.getSession().get("userName");
		if (userName==null)
			return "login";

		JSONArray jsonArray = new JSONArray();
		List users = userService.getUsers();
		User user = null;
		for (Object o : users){
			if (((User)o).getUserName().equals(userName)){
				user = (User) o;
				break;
			}
		}

		if (user!=null){
			Set sendFile = user.getSendFile();
			for (Object o : sendFile){
				UserFile file = (UserFile) o;
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("haoma",file.getFileId().toString());
				map.put("zhuangtai",file.getIsRead().toString());
				map.put("fujian","0");
				String name = userService.getUser(file.getFileFrom()).getUserName();
				map.put("fajianren",name);
				map.put("zhuti",file.getUserFileName());
				map.put("fajianshijian",file.getDate().toString());
				map.put("neirong",file.getContent());
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
		response.setContentType("json/javascript;charset=utf-8");

		ActionContext context = ServletActionContext.getContext();
		String userName	= (String) context.getSession().get("userName");
		if (userName==null)
			return "login";
		return null;
	}

	/**
	 * 标记文件为已读状态
	 */
	public String FileIsRead(){
		UserFile userFile = userFileService.getUserFile(Integer.parseInt(fileId));
		userFile.setIsRead(1);
		userFileService.updateUserFile(userFile);
		return null;
	}

}
