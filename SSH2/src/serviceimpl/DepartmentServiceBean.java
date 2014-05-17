package serviceimpl;

import bean.Department;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.DepartmentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */

@Transactional
public class DepartmentServiceBean implements DepartmentService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Department getDepartment(Integer departmentId) {
		Department department = null;
		try{
			department = (Department) sessionFactory.getCurrentSession().get(Department.class,departmentId);
		}catch (HibernateException e){
			return null;
		}
		return department;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getDepartments() {
		Query query = null;

		query = sessionFactory.getCurrentSession().createQuery("from Department");

		return null;
	}

	@Override
	public boolean addDepartment(Department department) {
		try{
			sessionFactory.getCurrentSession().persist(department);
		} catch (HibernateException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateDepartment(Department department) {
		return false;
	}

	@Override
	public boolean deleteDepartment(Department department) {
		return false;
	}
}
