package service;

import bean.Students;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yongjie on 14-5-22.
 */
public interface StudentsService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Students getStudent(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getStudents();

	boolean addStudent(Students students);

	boolean updateStudent(Students students);

	boolean deleteStudent(Students students);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List queryStudentBySchool(String schoolName);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List queryStudentByName(String studentName);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List queryStudentsByTime(Date startDate,Date endDate);
}
