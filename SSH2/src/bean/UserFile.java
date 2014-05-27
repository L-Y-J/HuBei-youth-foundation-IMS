package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-16.
 */

public class UserFile {
	Integer fileId;
	String userFileName;
	Integer fileFrom;
	Date date;
	Integer isRead;
	String content;
	Integer uploadFileId;

	Set fileTo = new HashSet();

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUploadFileId() {
		return uploadFileId;
	}

	public void setUploadFileId(Integer uploadFileId) {
		this.uploadFileId = uploadFileId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserFile)) return false;

		UserFile userFile = (UserFile) o;

		if (!fileId.equals(userFile.fileId)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return fileId.hashCode();
	}
}
