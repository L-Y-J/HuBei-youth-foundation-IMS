package unit;

import bean.MonthlyProgress;
import bean.Writer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MonthlyProgressService;
import service.WriterService;

import java.util.List;

/**
 * Created by yongjie on 14-5-18.
 */
public class WriterTest {

	private static WriterService writerService;
	private static MonthlyProgressService monthlyProgressService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			writerService = (WriterService) applicationcontext.getBean("WriterService");
			monthlyProgressService = (MonthlyProgressService) applicationcontext.getBean("MonthlyProgressService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addWriterTest(){

		Writer writer = new Writer();
		writer.setWriterName("作者甲");
		writer.setPhoneArea("027");
		writer.setPhone("3376006");
		writer.setCellPhone("18207132940");
		writer.setFaxArea("111");
		writer.setFax("22222222222");
		writer.setEmail("LYJ_LYJ@126.com");
		writer.setJobId(123);
		writerService.addWriter(writer);
	}


	@Test
	public void getWriterTest(){
		Writer writer = writerService.getWriter(124);
		System.out.println(writer.getWriterName());
	}

	@Test
	public void getWritersTest(){
		List list = writerService.getWriters();
		for (Object o : list){
			Writer writer = (Writer) o;
			System.out.println(writer.getWriterName());
		}
	}

	@Test
	public void updateWriterTest(){
		Writer writer = writerService.getWriter(124);
		writer.setWriterName("路人乙");
		boolean a = writerService.updateWriter(writer);
		System.out.println(a);
	}

	@Test
	public void deleteWriterTest(){
		Writer writer = writerService.getWriter(124);
		boolean a = writerService.deleteWriter(writer);
		System.out.println(a);
	}

	@Test
	public void Write_Monthlyproccess_Test(){
		Writer writer = writerService.getWriter(125);
		MonthlyProgress monthlyProgress = monthlyProgressService.getMonthlyProgress(180);
		writer.getMonthlyProgress().add(monthlyProgress);
//		monthlyProgressService.updateMonthlyProgress(monthlyProgress);
		writerService.updateWriter(writer);
	}






}
