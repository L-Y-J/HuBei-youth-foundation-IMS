package bean;

import java.util.Date;
import java.util.Set;

/**
 * Created by yongjie on 14-5-16.
 */

public class UserFile {
	Integer fileId;
	String userFileName;
	Integer fileFrom;
	Set fileTo;
	Date date;
	Integer isRead;

	public UserFile() {
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getUserFileName() {
		return userFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

	public Set getFileTo() {
		return fileTo;
	}

	public void setFileTo(Set fileTo) {
		this.fileTo = fileTo;
	}

	public Integer getFileFrom() {
		return fileFrom;
	}

	public void setFileFrom(Integer fileFrom) {
		this.fileFrom = fileFrom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}
