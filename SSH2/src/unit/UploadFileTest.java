package unit;

import bean.UploadFile;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UploadFileService;

import java.util.List;

/**
 * Created by yongjie on 14-5-26.
 */
public class UploadFileTest {

	private static UploadFileService uploadFileService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			uploadFileService = (UploadFileService) applicationcontext.getBean("UploadFileService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		UploadFile file = new UploadFile();
		file.setFileName("Test");
		uploadFileService.addUploadFile(file);
		uploadFileService.updateUploadFile(file);
		System.out.println(file.getId());
	}

	@Test
	public void getTest(){
		UploadFile file = uploadFileService.getUploadFile(262);
		System.out.println(file.getFileName());
	}

	@Test
	public void getsTest(){
		List uploadFiles = uploadFileService.getUploadFiles();
		System.out.println(uploadFiles.size());
	}

	@Test
	public void updateTest(){
		UploadFile file = uploadFileService.getUploadFile(262);
		file.setFileName("aaaaaaaa");
		uploadFileService.updateUploadFile(file);
	}

	@Test
	public void deleteTest(){
		UploadFile file = uploadFileService.getUploadFile(263);
		boolean b = uploadFileService.deleteUploadFile(file);
		System.out.println(b);
	}
}
