package service;

import bean.FileFoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */
public interface FileFoderService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	FileFoder getFoder(Integer foderId);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getFoders();

	boolean addFoder(FileFoder fileFoder);

	boolean updateFoder(FileFoder fileFoder);

	boolean deleteFoder(FileFoder fileFoder);
}
