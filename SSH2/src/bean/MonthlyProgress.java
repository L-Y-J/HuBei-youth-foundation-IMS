package bean; /***********************************************************************
 * Module:  MonthlyProgress.java
 * Author:  yongjie
 * Purpose: Defines the Class MonthlyProgress
 ***********************************************************************/

import java.util.*;

public class MonthlyProgress {
	public int monthlyProgressId;
    public Date writeDate;
    public int state;
    public int canDoneOnTime;
    public String description;
    public String signature;
    public String phoneAera;
    public String phone;

    public Integer writerId;
    public Integer subsidizeSchoolId;

	public MonthlyProgress() {
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getMonthlyProgressId() {
		return monthlyProgressId;
	}

	public void setMonthlyProgressId(int monthlyProgressId) {
		this.monthlyProgressId = monthlyProgressId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCanDoneOnTime() {
		return canDoneOnTime;
	}

	public void setCanDoneOnTime(int canDoneOnTime) {
		this.canDoneOnTime = canDoneOnTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getPhoneAera() {
		return phoneAera;
	}

	public void setPhoneAera(String phoneAera) {
		this.phoneAera = phoneAera;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getWriterId() {
		return writerId;
	}

	public void setWriterId(Integer writerId) {
		this.writerId = writerId;
	}

	public Integer getSubsidizeSchoolId() {
		return subsidizeSchoolId;
	}

	public void setSubsidizeSchoolId(Integer subsidizeSchoolId) {
		this.subsidizeSchoolId = subsidizeSchoolId;
	}
}