package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-18.
 */
public class Writer {

	Integer id;
	String writerName;
	String phoneArea;
	String phone;
	String cellPhone;
	String faxArea;
	String fax;
	String email;
	Integer jobId;
	Set monthlyProgress = new HashSet();

	public Writer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getPhoneArea() {
		return phoneArea;
	}

	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFaxArea() {
		return faxArea;
	}

	public void setFaxArea(String faxArea) {
		this.faxArea = faxArea;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Set getMonthlyProgress() {
		return monthlyProgress;
	}

	public void setMonthlyProgress(Set monthlyProgress) {
		this.monthlyProgress = monthlyProgress;
	}
}
