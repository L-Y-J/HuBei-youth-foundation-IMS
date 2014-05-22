package serviceimpl;

import bean.Photo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PhotoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */

@Transactional
public class PhotoServiceBean implements PhotoService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Photo getPhoto(Integer id) {
		Photo photo;
		try {
			photo = (Photo) sessionFactory.getCurrentSession().get(Photo.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return photo;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getPhotos() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Photo ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addPhoto(Photo photo) {
		try {
			sessionFactory.getCurrentSession().persist(photo);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePhoto(Photo photo) {
		try {
			sessionFactory.getCurrentSession().update(photo);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deletePhoto(Photo photo) {
		try {
			sessionFactory.getCurrentSession().delete(photo);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
