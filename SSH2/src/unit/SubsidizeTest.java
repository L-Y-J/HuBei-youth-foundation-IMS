package unit;

import bean.Subsidize;
import bean.SubsidizeSchool;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.SubsidizeSchoolService;
import service.SubsidizeService;

import java.util.Date;
import java.util.List;

/**
 * Created by yongjie on 14-5-22.
 */
public class SubsidizeTest {

	private static SubsidizeService subsidizeService;
	private static SubsidizeSchoolService schoolService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			subsidizeService = (SubsidizeService) applicationcontext.getBean("SubsidizeService");
			schoolService = (SubsidizeSchoolService) applicationcontext.getBean("SubsidizeSchoolService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		Subsidize subsidize = new Subsidize();
		subsidizeService.addSubsidize(subsidize);
	}

	@Test
	public void getTest(){
		Subsidize subsidize = subsidizeService.getSubsidize(209);
		System.out.println(subsidize.getSubsidizeId());
	}

	@Test
	public void getAddTest(){
		List subsidizes = subsidizeService.getSubsidizes();
		for (Object o : subsidizes){
			Subsidize subsidize = (Subsidize) o;
			System.out.println(subsidize.getSubsidizeId());
		}
	}

	@Test
	public void updateTest(){
		Subsidize subsidize = subsidizeService.getSubsidize(209);
		subsidize.setSubsidizeDate(new Date());
		subsidizeService.updateSubsidize(subsidize);
	}

	@Test
	public void deleteTest(){
		Subsidize subsidize = subsidizeService.getSubsidize(210);
		subsidizeService.deleteSubsidize(subsidize);
	}

	@Test
	public void Subsidize_School(){
		Subsidize subsidize = subsidizeService.getSubsidize(209);
		SubsidizeSchool school = schoolService.getSubsidizeSchool(162);
//		subsidize.getSchool().add(school);
//		subsidize.getSchool().clear();
//		subsidizeService.updateSubsidize(subsidize);
//		school.getSubsidize().add(subsidize);
		school.getSubsidize().clear();
		schoolService.updateSubsidizeSchool(school);
	}

}












