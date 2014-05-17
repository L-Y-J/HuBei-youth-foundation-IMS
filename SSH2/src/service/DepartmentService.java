package service;

import bean.Department;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */
public interface DepartmentService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Department getDepartment(Integer departmentId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getDepartments();

	boolean addDepartment(Department department);

	boolean updateDepartment(Department department);

	boolean deleteDepartment(Department department);
}
