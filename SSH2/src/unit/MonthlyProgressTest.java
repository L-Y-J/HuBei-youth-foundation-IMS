package unit;

import bean.MonthlyProgress;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MonthlyProgressService;

import java.util.Date;
import java.util.List;

/**
 * Created by yongjie on 14-5-20.
 */
public class MonthlyProgressTest {

	private static MonthlyProgressService monthlyProgressService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			monthlyProgressService = (MonthlyProgressService) applicationcontext.getBean("MonthlyProgressService");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		MonthlyProgress progress = new MonthlyProgress();
		progress.setWriteDate(new Date());
		progress.setState(0);
		progress.setCanDoneOnTime(1);
		progress.setDescription("test");
		progress.setSignature("Test");
		progress.setPhoneAera("027");
		progress.setPhone("1111111111");
		progress.setSubsidizeSchoolId(162);
		progress.setWriterId(125);
		boolean a = monthlyProgressService.addMonthlyProgress(progress);
		System.out.println(a);
	}

	@Test
	public void getTest(){
		MonthlyProgress monthlyProgress = monthlyProgressService.getMonthlyProgress(178);
		System.out.println(monthlyProgress.getMonthlyProgressId());
	}

	@Test
	public void getsTest(){
		List list = monthlyProgressService.getAllMonthlyProgress();
		for (Object o : list){
			MonthlyProgress progress = (MonthlyProgress) o;
			System.out.println(progress.getMonthlyProgressId());
		}
	}

	@Test
	public void updateTest(){
		MonthlyProgress monthlyProgress = monthlyProgressService.getMonthlyProgress(178);
		monthlyProgress.setWriteDate(new Date());
		boolean a = monthlyProgressService.updateMonthlyProgress(monthlyProgress);
		System.out.println(a);
	}

	@Test
	public void deleteTest(){
		MonthlyProgress monthlyProgress = monthlyProgressService.getMonthlyProgress(178);
		boolean a = monthlyProgressService.deleteMonthlyProgress(monthlyProgress);
		System.out.println(a);
	}
}













