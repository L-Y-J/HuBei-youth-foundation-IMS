package service;

import bean.Writer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yongjie on 14-5-18.
 */
public interface WriterService {

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	Writer getWriter(Integer id);

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	List getWriters();

	boolean addWriter(Writer writer);

	boolean updateWriter(Writer writer);

	boolean deleteWriter(Writer writer);
}
