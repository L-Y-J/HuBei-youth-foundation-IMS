package service;

import bean.UserFile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */
public interface UserFileService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	UserFile getUserFile(Integer userFileId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getUserFiles();

	boolean addUserFile(UserFile userFile);

	boolean updateUserFile(UserFile userFile);

	boolean deleteUserFile(UserFile userFile);
}
