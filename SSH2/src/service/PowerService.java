package service;

import bean.Power;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-13.
 */
public interface PowerService {
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Power getPower(Integer powerId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getPowers();

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Power getPowerByName(String powerName);


	boolean addPower(Power power);

	boolean deletePower(Power power);

	boolean updatePower(Power power);
}
