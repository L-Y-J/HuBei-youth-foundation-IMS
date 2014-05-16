package service;

import bean.Role;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-13.
 */
public interface RoleService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Role getRole(Integer roleId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getRoles();

	boolean deleteRole(Role role);

	boolean addRole(Role role);

	boolean updateRole(Role role);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Role getRoleByName(String roleName);
}
