package service;

import bean.UploadFile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-26.
 */
public interface UploadFileService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	UploadFile getUploadFile(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getUploadFiles();

	boolean addUploadFile(UploadFile uploadFile);

	boolean updateUploadFile(UploadFile uploadFile);

	boolean deleteUploadFile(UploadFile uploadFile);
}
