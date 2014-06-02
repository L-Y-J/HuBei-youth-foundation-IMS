package unit;

import bean.Subsidize;
import bean.SubsidizeSchool;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.SubsidizeSchoolService;
import service.SubsidizeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
//			Date d1 = dateFormat.parse("2012-01-01");
			Date d2 = dateFormat.parse("2014-01-01");
			Subsidize subsidize = new Subsidize();
			subsidize.setSubsidizeDate(d2);
			subsidizeService.addSubsidize(subsidize);
			subsidizeService.updateSubsidize(subsidize);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	@Test
	public void queryByDateTest(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = dateFormat.parse("2011-01-01");
			Date d2 = dateFormat.parse("2015-01-01");
			List set = subsidizeService.queryByDate(d1, d2);
			for (Object o : set){
				Subsidize s = (Subsidize) o;
				System.out.println(s.getSubsidizeDate());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}












