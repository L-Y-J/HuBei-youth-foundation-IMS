package unit;

import bean.SubsidizeSchool;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.SubsidizeSchoolService;

import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public class SubsidizeSchoolTest {

	private static SubsidizeSchoolService schoolService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			schoolService = (SubsidizeSchoolService) applicationcontext.getBean("SubsidizeSchoolService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		SubsidizeSchool school = new SubsidizeSchool();
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

}
