package service;

import bean.Subsidize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-22.
 */
public interface SubsidizeService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Subsidize getSubsidize(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getSubsidizes();

	boolean addSubsidize(Subsidize subsidize);

	boolean updateSubsidize(Subsidize subsidize);

	boolean deleteSubsidize(Subsidize subsidize);
}
