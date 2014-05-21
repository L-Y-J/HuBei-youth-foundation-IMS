package service;

import bean.MonthlyProgress;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-20.
 */
public interface MonthlyProgressService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	MonthlyProgress getMonthlyProgress(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getAllMonthlyProgress();

	boolean addMonthlyProgress(MonthlyProgress monthlyProgress);

	boolean updateMonthlyProgress(MonthlyProgress monthlyProgress);

	boolean deleteMonthlyProgress(MonthlyProgress monthlyProgress);
}
