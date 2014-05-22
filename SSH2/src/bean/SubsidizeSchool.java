package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-20.
 */
public class SubsidizeSchool {

	Integer schoolId;
	String schoolName;
	Date recordDate;
	Date completeDate;
	String province;
	String qu;
	String xian;
	String xiang;
	String cun;
	String localXiang;
	String localCun;
	Integer isMerge;
	String mergeSchool;
	String mergeVillages;
	String mergeHanlet;
	String mergeLocalXiang;
	String mergeLocalCun;
	String presidentName1;
	String presidentName2;
	String homePhoneArea;
	String homePhone;
	String officePhoneArea;
	String officePhone;
	String email1;
	String email2;
	String cellPhone1;
	String cellPhone2;
	String backgroundInformation;
	Integer numOfClass;
	Integer numOfStudent;
	Integer villageStudentsFrom;
	Integer areaStudentsFrom;
	Integer numOfSubsidizeStudent;
	Integer numOfTeacher;
	Integer numOfOfficeTeacher;
	Integer nunOfSubstituteTeacher;
	Integer numBoardingTeacher;
	Integer numOfBoardingStudent;
	Integer Covers;
	Integer buildingArea;
	Integer classroomArea;
	Date classroomCompleteDate;
	String classroomType;
	String classroomDanger;
	Integer studentDormitoryArea;
	Date studentDormitoryCompleteDate;
	String studentDormitoryType;
	String studentDormitoryDanger;
	Integer teacherDormitoryArea;
	Date teacherDormitoryCompleteDate;
	String teacherDormitoryType;
	String teacherDormitoryDanger;
	Integer canteenArea;
	Date canteenCompleteDate;
	String canteenType;
	String canteenDanger;
	Integer toiltArea;
	Date toiltCompleteDate;
	String toiltDanger;
	Integer toiltSituation;
	String other;
	Integer havePlayground;
	Integer teacherEducationPassRate;
	Integer teacherJobPassRate;
	Integer enrollmentOfStudent;
	Integer studentRetentionRate;
	Integer studentDropoutRate;
	Integer enrollmentRateOfStudent;
	Integer deskRate;
	Integer instrumentRate;
	Integer numOfBooks;
	Integer numOfComputer;
	String typeOfComputer;
	String sportsEquipmentSituation;
	Integer haveIntent;
	String Honor;
	Integer futureNumOfStudent;
	Integer futureNumOfBoardingStudent;
	Integer futureNumOfTeacher;
	Integer futureNumOfBoardingTeacher;
	String changeReason;
	Integer haveEngineering;
	Integer xianAverageIncome;
	Integer xiangAverageIncome;
	Integer cunAverageIncome;

	Set monthlyProgress = new HashSet();
	Set application = new HashSet();
	Set subsidize = new HashSet();

	public SubsidizeSchool() {
	}


	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public String getXiang() {
		return xiang;
	}

	public void setXiang(String xiang) {
		this.xiang = xiang;
	}

	public String getCun() {
		return cun;
	}

	public void setCun(String cun) {
		this.cun = cun;
	}

	public String getLocalXiang() {
		return localXiang;
	}

	public void setLocalXiang(String localXiang) {
		this.localXiang = localXiang;
	}

	public String getLocalCun() {
		return localCun;
	}

	public void setLocalCun(String localCun) {
		this.localCun = localCun;
	}

	public Integer getIsMerge() {
		return isMerge;
	}

	public void setIsMerge(Integer isMerge) {
		this.isMerge = isMerge;
	}

	public String getMergeSchool() {
		return mergeSchool;
	}

	public void setMergeSchool(String mergeSchool) {
		this.mergeSchool = mergeSchool;
	}

	public String getMergeVillages() {
		return mergeVillages;
	}

	public void setMergeVillages(String mergeVillages) {
		this.mergeVillages = mergeVillages;
	}

	public String getMergeHanlet() {
		return mergeHanlet;
	}

	public void setMergeHanlet(String mergeHanlet) {
		this.mergeHanlet = mergeHanlet;
	}

	public String getMergeLocalXiang() {
		return mergeLocalXiang;
	}

	public void setMergeLocalXiang(String mergeLocalXiang) {
		this.mergeLocalXiang = mergeLocalXiang;
	}

	public String getMergeLocalCun() {
		return mergeLocalCun;
	}

	public void setMergeLocalCun(String mergeLocalCun) {
		this.mergeLocalCun = mergeLocalCun;
	}

	public String getPresidentName1() {
		return presidentName1;
	}

	public void setPresidentName1(String presidentName1) {
		this.presidentName1 = presidentName1;
	}

	public String getPresidentName2() {
		return presidentName2;
	}

	public void setPresidentName2(String presidentName2) {
		this.presidentName2 = presidentName2;
	}

	public String getHomePhoneArea() {
		return homePhoneArea;
	}

	public void setHomePhoneArea(String homePhoneArea) {
		this.homePhoneArea = homePhoneArea;
	}

	public String getOfficePhoneArea() {
		return officePhoneArea;
	}

	public void setOfficePhoneArea(String officePhoneArea) {
		this.officePhoneArea = officePhoneArea;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getCellPhone1() {
		return cellPhone1;
	}

	public void setCellPhone1(String cellPhone1) {
		this.cellPhone1 = cellPhone1;
	}

	public String getCellPhone2() {
		return cellPhone2;
	}

	public void setCellPhone2(String cellPhone2) {
		this.cellPhone2 = cellPhone2;
	}

	public String getBackgroundInformation() {
		return backgroundInformation;
	}

	public void setBackgroundInformation(String backgroundInformation) {
		this.backgroundInformation = backgroundInformation;
	}

	public Integer getNumOfClass() {
		return numOfClass;
	}

	public void setNumOfClass(Integer numOfClass) {
		this.numOfClass = numOfClass;
	}

	public Integer getNumOfStudent() {
		return numOfStudent;
	}

	public void setNumOfStudent(Integer numOfStudent) {
		this.numOfStudent = numOfStudent;
	}

	public Integer getVillageStudentsFrom() {
		return villageStudentsFrom;
	}

	public void setVillageStudentsFrom(Integer villageStudentsFrom) {
		this.villageStudentsFrom = villageStudentsFrom;
	}

	public Integer getAreaStudentsFrom() {
		return areaStudentsFrom;
	}

	public void setAreaStudentsFrom(Integer areaStudentsFrom) {
		this.areaStudentsFrom = areaStudentsFrom;
	}

	public Integer getNumOfSubsidizeStudent() {
		return numOfSubsidizeStudent;
	}

	public void setNumOfSubsidizeStudent(Integer numOfSubsidizeStudent) {
		this.numOfSubsidizeStudent = numOfSubsidizeStudent;
	}

	public Integer getNumOfTeacher() {
		return numOfTeacher;
	}

	public void setNumOfTeacher(Integer numOfTeacher) {
		this.numOfTeacher = numOfTeacher;
	}

	public Integer getNumOfOfficeTeacher() {
		return numOfOfficeTeacher;
	}

	public void setNumOfOfficeTeacher(Integer numOfOfficeTeacher) {
		this.numOfOfficeTeacher = numOfOfficeTeacher;
	}

	public Integer getNumBoardingTeacher() {
		return numBoardingTeacher;
	}

	public void setNumBoardingTeacher(Integer numBoardingTeacher) {
		this.numBoardingTeacher = numBoardingTeacher;
	}

	public Integer getNunOfSubstituteTeacher() {
		return nunOfSubstituteTeacher;
	}

	public void setNunOfSubstituteTeacher(Integer nunOfSubstituteTeacher) {
		this.nunOfSubstituteTeacher = nunOfSubstituteTeacher;
	}

	public Integer getNumOfBoardingStudent() {
		return numOfBoardingStudent;
	}

	public void setNumOfBoardingStudent(Integer numOfBoardingStudent) {
		this.numOfBoardingStudent = numOfBoardingStudent;
	}

	public Integer getCovers() {
		return Covers;
	}

	public void setCovers(Integer covers) {
		Covers = covers;
	}

	public Integer getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Integer buildingArea) {
		this.buildingArea = buildingArea;
	}

	public Integer getClassroomArea() {
		return classroomArea;
	}

	public void setClassroomArea(Integer classroomArea) {
		this.classroomArea = classroomArea;
	}

	public Date getClassroomCompleteDate() {
		return classroomCompleteDate;
	}

	public void setClassroomCompleteDate(Date classroomCompleteDate) {
		this.classroomCompleteDate = classroomCompleteDate;
	}

	public String getClassroomType() {
		return classroomType;
	}

	public void setClassroomType(String classroomType) {
		this.classroomType = classroomType;
	}

	public String getClassroomDanger() {
		return classroomDanger;
	}

	public void setClassroomDanger(String classroomDanger) {
		this.classroomDanger = classroomDanger;
	}

	public Integer getStudentDormitoryArea() {
		return studentDormitoryArea;
	}

	public void setStudentDormitoryArea(Integer studentDormitoryArea) {
		this.studentDormitoryArea = studentDormitoryArea;
	}

	public Date getStudentDormitoryCompleteDate() {
		return studentDormitoryCompleteDate;
	}

	public void setStudentDormitoryCompleteDate(Date studentDormitoryCompleteDate) {
		this.studentDormitoryCompleteDate = studentDormitoryCompleteDate;
	}

	public String getStudentDormitoryType() {
		return studentDormitoryType;
	}

	public void setStudentDormitoryType(String studentDormitoryType) {
		this.studentDormitoryType = studentDormitoryType;
	}

	public String getStudentDormitoryDanger() {
		return studentDormitoryDanger;
	}

	public void setStudentDormitoryDanger(String studentDormitoryDanger) {
		this.studentDormitoryDanger = studentDormitoryDanger;
	}

	public Integer getTeacherDormitoryArea() {
		return teacherDormitoryArea;
	}

	public void setTeacherDormitoryArea(Integer teacherDormitoryArea) {
		this.teacherDormitoryArea = teacherDormitoryArea;
	}

	public Date getTeacherDormitoryCompleteDate() {
		return teacherDormitoryCompleteDate;
	}

	public void setTeacherDormitoryCompleteDate(Date teacherDormitoryCompleteDate) {
		this.teacherDormitoryCompleteDate = teacherDormitoryCompleteDate;
	}

	public String getTeacherDormitoryType() {
		return teacherDormitoryType;
	}

	public void setTeacherDormitoryType(String teacherDormitoryType) {
		this.teacherDormitoryType = teacherDormitoryType;
	}

	public Integer getCanteenArea() {
		return canteenArea;
	}

	public void setCanteenArea(Integer canteenArea) {
		this.canteenArea = canteenArea;
	}

	public String getTeacherDormitoryDanger() {
		return teacherDormitoryDanger;
	}

	public void setTeacherDormitoryDanger(String teacherDormitoryDanger) {
		this.teacherDormitoryDanger = teacherDormitoryDanger;
	}

	public Date getCanteenCompleteDate() {
		return canteenCompleteDate;
	}

	public void setCanteenCompleteDate(Date canteenCompleteDate) {
		this.canteenCompleteDate = canteenCompleteDate;
	}

	public String getCanteenType() {
		return canteenType;
	}

	public void setCanteenType(String canteenType) {
		this.canteenType = canteenType;
	}

	public String getCanteenDanger() {
		return canteenDanger;
	}

	public void setCanteenDanger(String canteenDanger) {
		this.canteenDanger = canteenDanger;
	}

	public Integer getToiltArea() {
		return toiltArea;
	}

	public void setToiltArea(Integer toiltArea) {
		this.toiltArea = toiltArea;
	}

	public Date getToiltCompleteDate() {
		return toiltCompleteDate;
	}

	public void setToiltCompleteDate(Date toiltCompleteDate) {
		this.toiltCompleteDate = toiltCompleteDate;
	}

	public String getToiltDanger() {
		return toiltDanger;
	}

	public void setToiltDanger(String toiltDanger) {
		this.toiltDanger = toiltDanger;
	}

	public Integer getToiltSituation() {
		return toiltSituation;
	}

	public void setToiltSituation(Integer toiltSituation) {
		this.toiltSituation = toiltSituation;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getHavePlayground() {
		return havePlayground;
	}

	public void setHavePlayground(Integer havePlayground) {
		this.havePlayground = havePlayground;
	}

	public Integer getTeacherEducationPassRate() {
		return teacherEducationPassRate;
	}

	public void setTeacherEducationPassRate(Integer teacherEducationPassRate) {
		this.teacherEducationPassRate = teacherEducationPassRate;
	}

	public Integer getTeacherJobPassRate() {
		return teacherJobPassRate;
	}

	public void setTeacherJobPassRate(Integer teacherJobPassRate) {
		this.teacherJobPassRate = teacherJobPassRate;
	}

	public Integer getEnrollmentOfStudent() {
		return enrollmentOfStudent;
	}

	public void setEnrollmentOfStudent(Integer enrollmentOfStudent) {
		this.enrollmentOfStudent = enrollmentOfStudent;
	}

	public Integer getStudentRetentionRate() {
		return studentRetentionRate;
	}

	public void setStudentRetentionRate(Integer studentRetentionRate) {
		this.studentRetentionRate = studentRetentionRate;
	}

	public Integer getStudentDropoutRate() {
		return studentDropoutRate;
	}

	public void setStudentDropoutRate(Integer studentDropoutRate) {
		this.studentDropoutRate = studentDropoutRate;
	}

	public Integer getEnrollmentRateOfStudent() {
		return enrollmentRateOfStudent;
	}

	public void setEnrollmentRateOfStudent(Integer enrollmentRateOfStudent) {
		this.enrollmentRateOfStudent = enrollmentRateOfStudent;
	}

	public Integer getDeskRate() {
		return deskRate;
	}

	public void setDeskRate(Integer deskRate) {
		this.deskRate = deskRate;
	}

	public Integer getInstrumentRate() {
		return instrumentRate;
	}

	public void setInstrumentRate(Integer instrumentRate) {
		this.instrumentRate = instrumentRate;
	}

	public Integer getNumOfBooks() {
		return numOfBooks;
	}

	public void setNumOfBooks(Integer numOfBooks) {
		this.numOfBooks = numOfBooks;
	}

	public Integer getNumOfComputer() {
		return numOfComputer;
	}

	public void setNumOfComputer(Integer numOfComputer) {
		this.numOfComputer = numOfComputer;
	}

	public String getTypeOfComputer() {
		return typeOfComputer;
	}

	public void setTypeOfComputer(String typeOfComputer) {
		this.typeOfComputer = typeOfComputer;
	}

	public String getSportsEquipmentSituation() {
		return sportsEquipmentSituation;
	}

	public void setSportsEquipmentSituation(String sportsEquipmentSituation) {
		this.sportsEquipmentSituation = sportsEquipmentSituation;
	}

	public Integer getHaveIntent() {
		return haveIntent;
	}

	public void setHaveIntent(Integer haveIntent) {
		this.haveIntent = haveIntent;
	}

	public String getHonor() {
		return Honor;
	}

	public void setHonor(String honor) {
		Honor = honor;
	}

	public Integer getFutureNumOfStudent() {
		return futureNumOfStudent;
	}

	public void setFutureNumOfStudent(Integer futureNumOfStudent) {
		this.futureNumOfStudent = futureNumOfStudent;
	}

	public Integer getFutureNumOfBoardingStudent() {
		return futureNumOfBoardingStudent;
	}

	public void setFutureNumOfBoardingStudent(Integer futureNumOfBoardingStudent) {
		this.futureNumOfBoardingStudent = futureNumOfBoardingStudent;
	}

	public Integer getFutureNumOfTeacher() {
		return futureNumOfTeacher;
	}

	public void setFutureNumOfTeacher(Integer futureNumOfTeacher) {
		this.futureNumOfTeacher = futureNumOfTeacher;
	}

	public Integer getFutureNumOfBoardingTeacher() {
		return futureNumOfBoardingTeacher;
	}

	public void setFutureNumOfBoardingTeacher(Integer futureNumOfBoardingTeacher) {
		this.futureNumOfBoardingTeacher = futureNumOfBoardingTeacher;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Integer getHaveEngineering() {
		return haveEngineering;
	}

	public void setHaveEngineering(Integer haveEngineering) {
		this.haveEngineering = haveEngineering;
	}

	public Integer getXianAverageIncome() {
		return xianAverageIncome;
	}

	public void setXianAverageIncome(Integer xianAverageIncome) {
		this.xianAverageIncome = xianAverageIncome;
	}

	public Integer getXiangAverageIncome() {
		return xiangAverageIncome;
	}

	public void setXiangAverageIncome(Integer xiangAverageIncome) {
		this.xiangAverageIncome = xiangAverageIncome;
	}

	public Integer getCunAverageIncome() {
		return cunAverageIncome;
	}

	public void setCunAverageIncome(Integer cunAverageIncome) {
		this.cunAverageIncome = cunAverageIncome;
	}

	public Set getMonthlyProgress() {
		return monthlyProgress;
	}

	public void setMonthlyProgress(Set monthlyProgress) {
		this.monthlyProgress = monthlyProgress;
	}

	public Set getApplication() {
		return application;
	}

	public void setApplication(Set application) {
		this.application = application;
	}

	public Set getSubsidize() {
		return subsidize;
	}

	public void setSubsidize(Set subsidize) {
		this.subsidize = subsidize;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SubsidizeSchool)) return false;

		SubsidizeSchool school = (SubsidizeSchool) o;

		if (!schoolId.equals(school.schoolId)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return schoolId.hashCode();
	}
}




















