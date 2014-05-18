package serviceimpl;

import bean.Job;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.JobService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongjie on 14-5-18.
 */
@Transactional
public class JobServiceBean implements JobService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public Job getJob(Integer jobId) {
		Job job = null;
		try {
			job = (Job) sessionFactory.getCurrentSession().get(Job.class,jobId);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return job;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getJobs() {
		Query query = null;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from Job");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().persist(job);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
