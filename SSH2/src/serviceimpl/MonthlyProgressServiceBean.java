package serviceimpl;

import bean.MonthlyProgress;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.MonthlyProgressService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-20.
 */

@Transactional
public class MonthlyProgressServiceBean implements MonthlyProgressService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public MonthlyProgress getMonthlyProgress(Integer id) {
		MonthlyProgress progress;
		try {
			progress = (MonthlyProgress)sessionFactory.getCurrentSession().get(MonthlyProgress.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return progress;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getAllMonthlyProgress() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from MonthlyProgress ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addMonthlyProgress(MonthlyProgress monthlyProgress) {
		try {
			sessionFactory.getCurrentSession().persist(monthlyProgress);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateMonthlyProgress(MonthlyProgress monthlyProgress) {
		try {
			sessionFactory.getCurrentSession().update(monthlyProgress);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMonthlyProgress(MonthlyProgress monthlyProgress) {
		try {
			sessionFactory.getCurrentSession().delete(monthlyProgress);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
