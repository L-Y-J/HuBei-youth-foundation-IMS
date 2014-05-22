package service;

import bean.Application;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public interface AppilcationService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Application getApplication(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getApplications();

	boolean addApplication(Application application);

	boolean updateApplication(Application application);

	boolean deleteApplication(Application application);
}
