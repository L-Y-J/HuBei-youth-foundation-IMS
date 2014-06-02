package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-22.
 */
public class Students {

	Integer studentId;
	String studentName;
	String school;
	Integer studentState;
	Date recordDate;
	String connectStudent;

	Set subsidize = new HashSet();

	public Students() {
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getStudentState() {
		return studentState;
	}

	public void setStudentState(Integer studentState) {
		this.studentState = studentState;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Set getSubsidize() {
		return subsidize;
	}

	public void setSubsidize(Set subsidize) {
		this.subsidize = subsidize;
	}

	public String getConnectStudent() {
		return connectStudent;
	}

	public void setConnectStudent(String connectStudent) {
		this.connectStudent = connectStudent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Students)) return false;

		Students students = (Students) o;

		if (!studentId.equals(students.studentId)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return studentId.hashCode();
	}
}
