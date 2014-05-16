package serviceimpl;

import bean.Role;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-13.
 */

@Transactional
public class RoleServiceBean implements service.RoleService {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Role getRole(Integer roleId){
		Role role = null;
		try {
			role = (Role) sessionFactory.getCurrentSession().get(Role.class,roleId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return role;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getRoles(){
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Role");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean deleteRole(Role role){
		try {
			sessionFactory.getCurrentSession().delete(role);
		} catch (HibernateException e) {
			e.printStackTrace();
			return  false;
		}
		return true;
	}

	@Override
	public boolean addRole(Role role){
		try {
			sessionFactory.getCurrentSession().persist(role);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateRole(Role role){
		try {
			sessionFactory.getCurrentSession().update(role);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Role getRoleByName(String roleName) {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Role where roleName=?");
			query.setString(0,roleName);
			return (Role)query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}


}
