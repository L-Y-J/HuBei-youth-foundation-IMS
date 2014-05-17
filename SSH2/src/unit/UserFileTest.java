package unit;

import bean.UserFile;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserFileService;

import java.util.Date;
import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */
public class UserFileTest {

	private static UserFileService userFileService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			userFileService = (UserFileService) applicationcontext.getBean("UserFileService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addUserFileTest(){
		UserFile userFile = new UserFile();
		userFile.setUserFileName("测试文件1");
		userFile.setDate(new Date());
		userFile.setIsRead(0);

		boolean a = userFileService.addUserFile(userFile);
		System.out.println(a);
	}

	@Test
	public void getUserFileTest(){
		UserFile userFile = userFileService.getUserFile(92);
		System.out.println(userFile.getUserFileName());
		System.out.println(userFile.getDate());
	}

	@Test
	public void getUserFiles(){
		List list = userFileService.getUserFiles();
		for (Object o : list){
			UserFile userFile = (UserFile) o;
			System.out.println(userFile.getDate());
		}
	}

	@Test
	public void updateUserFile(){
		UserFile userFile = userFileService.getUserFile(93);
		userFile.setUserFileName("公告文件");
		userFileService.updateUserFile(userFile);
	}

	@Test
	public void deleteUserFileTest(){
		UserFile userFile = userFileService.getUserFile(92);
		userFileService.deleteUserFile(userFile);
	}

}










