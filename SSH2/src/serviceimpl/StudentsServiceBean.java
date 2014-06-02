package serviceimpl;

import bean.Students;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.StudentsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-22.
 */

@Transactional
public class StudentsServiceBean implements StudentsService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Students getStudent(Integer id) {
		Students students;
		try {
			students = (Students) sessionFactory.getCurrentSession().get(Students.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return students;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getStudents() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Students ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addStudent(Students students) {
		try {
			sessionFactory.getCurrentSession().persist(students);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateStudent(Students students) {
		try {
			sessionFactory.getCurrentSession().update(students);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteStudent(Students students) {
		try {
			sessionFactory.getCurrentSession().delete(students);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List queryStudentBySchool(String schoolName) {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Students where school=?");
			query.setString(0,schoolName);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List queryStudentByName(String studentName) {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Students where studentName=?");
			query.setString(0,studentName);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}
