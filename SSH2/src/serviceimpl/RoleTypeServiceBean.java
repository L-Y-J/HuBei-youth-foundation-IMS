package serviceimpl;

import bean.RoleType;
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
public class RoleTypeServiceBean implements service.RoleTypeService {

	@Resource
	SessionFactory sessionFactory;


	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public RoleType getRoleType(Integer roleTypeId){
		RoleType roleType = null;
		try {
			roleType = (RoleType) sessionFactory.getCurrentSession().get(RoleType.class,roleTypeId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return roleType;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getRoleTypes(){
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from RoleType");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addRoleType(RoleType roleType){
		try {
			sessionFactory.getCurrentSession().persist(roleType);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateRoleType(RoleType roleType){
		try {
			sessionFactory.getCurrentSession().update(roleType);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteRoleType(RoleType roleType){
		try {
			sessionFactory.getCurrentSession().delete(roleType);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getRoleTypeByName(String name) {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from RoleType where typeName=?");
			query.setString(0,name);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return query.list();
	}

}
