package serviceimpl;

import bean.Writer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.WriterService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-18.
 */

@Transactional
public class WriterServiceBean implements WriterService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Writer getWriter(Integer id) {
		Writer writer = null;
		try {
			writer = (Writer) sessionFactory.getCurrentSession().get(Writer.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return writer;
	}

	@Override
	public List getWriters() {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Writer");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addWriter(Writer writer) {
		try {
			sessionFactory.getCurrentSession().persist(writer);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateWriter(Writer writer) {
		try {
			sessionFactory.getCurrentSession().update(writer);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteWriter(Writer writer) {
		try {
			sessionFactory.getCurrentSession().delete(writer);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
