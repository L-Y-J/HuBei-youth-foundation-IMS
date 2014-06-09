package service;

import bean.Job;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-18.
 */
public interface JobService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Job getJob(Integer jobId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getJobs();

	boolean addJob(Job job);

	boolean updateJob(Job job);

	boolean deleteJob(Job job);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Job queryJobByName(String name);
}
