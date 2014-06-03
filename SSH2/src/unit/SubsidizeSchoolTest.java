package unit;

import bean.Application;
import bean.SubsidizeSchool;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AppilcationService;
import service.SubsidizeSchoolService;

import java.util.Date;
import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public class SubsidizeSchoolTest {

	private static SubsidizeSchoolService schoolService;
	private static AppilcationService appilcationService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			schoolService = (SubsidizeSchoolService) applicationcontext.getBean("SubsidizeSchoolService");
			appilcationService = (AppilcationService) applicationcontext.getBean("AppilcationService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		SubsidizeSchool school = new SubsidizeSchool();
		school.setRecordDate(new Date());
		boolean a = schoolService.addSubsidizeSchool(school);
		System.out.println(a);
	}

	@Test
	public void getTest(){
		SubsidizeSchool subsidizeSchool = schoolService.getSubsidizeSchool(162);
		System.out.println(subsidizeSchool.getSchoolId());
	}

	@Test
	public void getsTest(){
		List list = schoolService.getSubsidizeSchools();
		for (Object o : list){
			SubsidizeSchool school = (SubsidizeSchool) o;
			System.out.println(school.getSchoolId());
		}
	}

	@Test
	public void updateTest(){
		SubsidizeSchool subsidizeSchool = schoolService.getSubsidizeSchool(162);
		subsidizeSchool.setSchoolName("希望小学");
		boolean a = schoolService.updateSubsidizeSchool(subsidizeSchool);
		System.out.println(a);
	}

	@Test
	public void deleteTest(){
		SubsidizeSchool subsidizeSchool = schoolService.getSubsidizeSchool(179);
		boolean a = schoolService.deleteSubsidizeSchool(subsidizeSchool);
		System.out.println(a);
	}

	@Test
	public void application_SubSchool(){
		SubsidizeSchool subsidizeSchool = schoolService.getSubsidizeSchool(162);
//		Application application = appilcationService.getApplication(204);
//		subsidizeSchool.getApplication().add(application);
		subsidizeSchool.getApplication().clear();
		schoolService.updateSubsidizeSchool(subsidizeSchool);
	}

}
