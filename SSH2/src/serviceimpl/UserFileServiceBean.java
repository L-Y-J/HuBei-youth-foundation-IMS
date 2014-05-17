package serviceimpl;

import bean.UserFile;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserFileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */

@Transactional
public class UserFileServiceBean implements UserFileService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public UserFile getUserFile(Integer userFileId) {
		UserFile userFile = null;
		try {
			userFile = (UserFile) sessionFactory.getCurrentSession().get(UserFile.class,userFileId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return userFile;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getUserFiles() {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from UserFile");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addUserFile(UserFile userFile) {
		try {
			sessionFactory.getCurrentSession().persist(userFile);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserFile(UserFile userFile) {
		try {
			sessionFactory.getCurrentSession().update(userFile);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteUserFile(UserFile userFile) {
		try {
			sessionFactory.getCurrentSession().delete(userFile);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
