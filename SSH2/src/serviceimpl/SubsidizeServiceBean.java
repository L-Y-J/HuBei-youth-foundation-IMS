package serviceimpl;

import bean.Subsidize;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.SubsidizeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-22.
 */

@Transactional
public class SubsidizeServiceBean implements SubsidizeService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Subsidize getSubsidize(Integer id) {
		Subsidize subsidize;
		try {
			subsidize = (Subsidize) sessionFactory.getCurrentSession().get(Subsidize.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return subsidize;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getSubsidizes() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Subsidize ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addSubsidize(Subsidize subsidize) {
		try {
			sessionFactory.getCurrentSession().persist(subsidize);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateSubsidize(Subsidize subsidize) {
		try {
			sessionFactory.getCurrentSession().update(subsidize);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSubsidize(Subsidize subsidize) {
		try {
			sessionFactory.getCurrentSession().delete(subsidize);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
