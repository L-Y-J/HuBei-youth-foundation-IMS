package serviceimpl;

import bean.Application;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.AppilcationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */

@Transactional
public class AppilcationServiceBean implements AppilcationService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Application getApplication(Integer id) {
		Application application;
		try {
			application = (Application) sessionFactory.getCurrentSession().get(Application.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return application;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getApplications() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Application ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addApplication(Application application) {
		try {
			sessionFactory.getCurrentSession().persist(application);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateApplication(Application application) {
		try {
			sessionFactory.getCurrentSession().update(application);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteApplication(Application application) {
		try {
			sessionFactory.getCurrentSession().delete(application);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
