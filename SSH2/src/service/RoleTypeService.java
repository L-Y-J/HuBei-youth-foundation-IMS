package service;

import bean.RoleType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-13.
 */
public interface RoleTypeService {
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	RoleType getRoleType(Integer roleTypeId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getRoleTypes();

	boolean addRoleType(RoleType roleType);

	boolean updateRoleType(RoleType roleType);

	boolean deleteRoleType(RoleType roleType);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getRoleTypeByName(String name);
}
