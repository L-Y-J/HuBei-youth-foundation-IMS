package service;

import bean.SubsidizeSchool;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public interface SubsidizeSchoolService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	SubsidizeSchool getSubsidizeSchool(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getSubsidizeSchools();

	boolean addSubsidizeSchool(SubsidizeSchool school);

	boolean updateSubsidizeSchool(SubsidizeSchool school);

	boolean deleteSubsidizeSchool(SubsidizeSchool school);
}
