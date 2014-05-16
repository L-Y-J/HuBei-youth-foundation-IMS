package serviceimpl;

import bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-12.
 */
@Transactional
public class UserServiceBean implements service.UserService {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public User getUser(Integer userId) {
		User user = null;
		try {
			user = (User) sessionFactory.getCurrentSession().get(User.class,userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List getUsers() {
		Query query = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			query = currentSession.createQuery("from User");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List queryNameAndPassword(String userName,String password) {
		Query query = null;
		query = sessionFactory.getCurrentSession().createQuery("from User where userName=? and password=?");
		query.setString(0,userName);
		query.setString(1,password);
		return query.list();
	}

}











