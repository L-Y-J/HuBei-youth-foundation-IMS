package serviceimpl;

import bean.Power;
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
public class PowerServiceBean implements service.PowerService {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Power getPower(Integer powerId){
		Power power=null;
		try {
			power = (Power) sessionFactory.getCurrentSession().get(Power.class,powerId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return power;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getPowers(){
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Power");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Power getPowerByName(String powerName) {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Power where powerName=?");
			query.setString(0,powerName);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return (Power)query.list().get(0);
	}

	@Override
	public Boolean addPower(Power power){
		try {
			sessionFactory.getCurrentSession().persist(power);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deletePower(Power power){
		try {
			sessionFactory.getCurrentSession().delete(power);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePower(Power power){
		try {
			sessionFactory.getCurrentSession().update(power);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}











}














