package serviceimpl;

import bean.FileFoder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.FileFoderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */

@Transactional
public class FileFoderServiceBean implements FileFoderService {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public FileFoder getFoder(Integer foderId) {
		FileFoder fileFoder;
		try {
			fileFoder = (FileFoder) sessionFactory.getCurrentSession().get(FileFoder.class,foderId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return fileFoder;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getFoders() {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from FileFoder");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addFoder(FileFoder fileFoder) {
		try {
			sessionFactory.getCurrentSession().persist(fileFoder);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateFoder(FileFoder fileFoder) {
		try {
			sessionFactory.getCurrentSession().update(fileFoder);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteFoder(FileFoder fileFoder) {
		try {
			sessionFactory.getCurrentSession().delete(fileFoder);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
