package unit;

import bean.Application;
import bean.Photo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AppilcationService;
import service.PhotoService;

import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public class ApplicationTest {

	private static AppilcationService appilcationService;
	private static PhotoService photoService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			appilcationService = (AppilcationService) applicationcontext.getBean("AppilcationService");
			photoService = (PhotoService) applicationcontext.getBean("PhotoService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		Application application = new Application();
		boolean a = appilcationService.addApplication(application);
		System.out.println(a);
	}

	@Test
	public void getTest(){
		Application application = appilcationService.getApplication(202);
		System.out.println(application.getAppilcationId());
	}

	@Test
	public void getAllTest(){
		List applications = appilcationService.getApplications();
		for (Object o : applications){
			Application application = (Application) o;
			System.out.println(application.getAppilcationId());
		}
	}

	@Test
	public void updateTest(){
		Application application = appilcationService.getApplication(202);
		application.setState("1");
		appilcationService.updateApplication(application);
	}

	@Test
	public void deleteTest(){
		Application application = appilcationService.getApplication(202);
		boolean a = appilcationService.deleteApplication(application);
		System.out.println(a);
	}

	@Test
	public void Applition_Photo_Test(){
		Application application = appilcationService.getApplication(204);
//		Photo photo = photoService.getPhoto(207);
//		application.getPhoto().add(photo);
		application.getPhoto().clear();
		appilcationService.updateApplication(application);
	}
}






















