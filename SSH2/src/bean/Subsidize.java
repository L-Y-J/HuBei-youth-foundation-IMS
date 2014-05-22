package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-21.
 */
public class Subsidize {

	public int SubsidizeId;
	public java.lang.String Subsidizer;
	public java.lang.String SubsidizeProvince;
	public java.util.Date SubsidizeDate;
	public int SubsidizeMoney;
	public java.lang.String SubsidizeItem;
	public java.lang.String SubsidizeDapartment;
	public int IsForeign;
	public int IsNation;
	public int IsPersonal;
	public java.lang.String Remark;
	public java.lang.String State;
	public java.lang.String SubsidizerPlace;

	public Set school = new HashSet();
	public Set students = new HashSet();

	public Subsidize() {
	}

	public int getSubsidizeId() {
		return SubsidizeId;
	}

	public void setSubsidizeId(int subsidizeId) {
		SubsidizeId = subsidizeId;
	}

	public String getSubsidizer() {
		return Subsidizer;
	}

	public void setSubsidizer(String subsidizer) {
		Subsidizer = subsidizer;
	}

	public String getSubsidizeProvince() {
		return SubsidizeProvince;
	}

	public void setSubsidizeProvince(String subsidizeProvince) {
		SubsidizeProvince = subsidizeProvince;
	}

	public Date getSubsidizeDate() {
		return SubsidizeDate;
	}

	public void setSubsidizeDate(Date subsidizeDate) {
		SubsidizeDate = subsidizeDate;
	}

	public int getSubsidizeMoney() {
		return SubsidizeMoney;
	}

	public void setSubsidizeMoney(int subsidizeMoney) {
		SubsidizeMoney = subsidizeMoney;
	}

	public String getSubsidizeItem() {
		return SubsidizeItem;
	}

	public void setSubsidizeItem(String subsidizeItem) {
		SubsidizeItem = subsidizeItem;
	}

	public String getSubsidizeDapartment() {
		return SubsidizeDapartment;
	}

	public void setSubsidizeDapartment(String subsidizeDapartment) {
		SubsidizeDapartment = subsidizeDapartment;
	}

	public int getIsForeign() {
		return IsForeign;
	}

	public void setIsForeign(int isForeign) {
		IsForeign = isForeign;
	}

	public int getIsNation() {
		return IsNation;
	}

	public void setIsNation(int isNation) {
		IsNation = isNation;
	}

	public int getIsPersonal() {
		return IsPersonal;
	}

	public void setIsPersonal(int isPersonal) {
		IsPersonal = isPersonal;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getSubsidizerPlace() {
		return SubsidizerPlace;
	}

	public void setSubsidizerPlace(String subsidizerPlace) {
		SubsidizerPlace = subsidizerPlace;
	}

	public Set getSchool() {
		return school;
	}

	public void setSchool(Set school) {
		this.school = school;
	}

	public Set getStudents() {
		return students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}
}
