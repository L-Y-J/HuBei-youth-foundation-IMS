package unit;

import bean.FileFoder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.FileFoderService;

import java.util.List;

/**
 * Created by yongjie on 14-5-17.
 */
public class FileFoderTest {

	private static FileFoderService fileFoderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			fileFoderService = (FileFoderService) applicationcontext.getBean("FileFoderService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void addFileFoderTest(){
		FileFoder fileFoder = new FileFoder();
		fileFoder.setFoderName("垃圾箱");
		boolean a = fileFoderService.addFoder(fileFoder);
		System.out.println(a);
	}

	@Test
	public void getFileFoderTest(){
		FileFoder foder = fileFoderService.getFoder(81);
		System.out.println(foder.getFoderName());
	}

	@Test
	public void getFileFodersTest(){
		List foders = fileFoderService.getFoders();
		if (foders != null){
			for (Object foder : foders){
				FileFoder f = (FileFoder) foder;
				System.out.println(f.getFoderName());
			}
		}
	}

	@Test
	public void updateFileFoderTest(){
		FileFoder fileFoder = fileFoderService.getFoder(81);
		fileFoder.setFoderName("垃圾箱");
		boolean a = fileFoderService.updateFoder(fileFoder);
		System.out.println(a);
	}

	@Test
	public void deleteFileFoderTest(){
		FileFoder fileFoder = fileFoderService.getFoder(81);
		boolean a = fileFoderService.deleteFoder(fileFoder);
		System.out.println(a);
	}


}
















