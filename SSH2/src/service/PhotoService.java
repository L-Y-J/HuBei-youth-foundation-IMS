package service;

import bean.Photo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public interface PhotoService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Photo getPhoto(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getPhotos();

	boolean addPhoto(Photo photo);

	boolean updatePhoto(Photo photo);

	boolean deletePhoto(Photo photo);
}
