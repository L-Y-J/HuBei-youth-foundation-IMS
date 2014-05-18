package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-17.
 */
public class Department {

	Integer id;
	String departmentName;
	String leval;

	Set job = new HashSet();

	public Department() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLeval() {
		return leval;
	}

	public void setLeval(String leval) {
		this.leval = leval;
	}

	public Set getJob() {
		return job;
	}

	public void setJob(Set job) {
		this.job = job;
	}
}
