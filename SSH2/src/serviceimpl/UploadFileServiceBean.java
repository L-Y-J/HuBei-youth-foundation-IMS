package serviceimpl;

import bean.UploadFile;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UploadFileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-26.
 */

@Transactional
public class UploadFileServiceBean implements UploadFileService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public UploadFile getUploadFile(Integer id) {
		UploadFile uploadFile = null;
		try {
			uploadFile = (UploadFile) sessionFactory.getCurrentSession().get(UploadFile.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return uploadFile;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getUploadFiles() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from UploadFile ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addUploadFile(UploadFile uploadFile) {
		try {
			sessionFactory.getCurrentSession().persist(uploadFile);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUploadFile(UploadFile uploadFile) {
		try {
			sessionFactory.getCurrentSession().update(uploadFile);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteUploadFile(UploadFile uploadFile) {
		try {
			sessionFactory.getCurrentSession().delete(uploadFile);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
