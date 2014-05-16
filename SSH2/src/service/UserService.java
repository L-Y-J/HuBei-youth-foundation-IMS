package service;

import bean.Power;
import bean.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by yongjie on 14-5-12.
 */
public interface UserService {
	boolean addUser(User user);

	boolean deleteUser(User user);

	boolean updateUser(User user);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	User getUser(Integer userId);

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	List getUsers();

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	List queryNameAndPassword(String userName,String password);
}
