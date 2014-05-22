package unit;

import bean.Photo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.PhotoService;

import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */
public class PhotoTest {

	private static PhotoService photoService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			photoService = (PhotoService) applicationcontext.getBean("PhotoService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		Photo photo = new Photo();
//		photo.setPhotoName("测试图片");
		boolean a = photoService.addPhoto(photo);
		System.out.println(a);
	}

	@Test
	public void getPhoto(){
		Photo photo = photoService.getPhoto(207);
		System.out.println(photo.getPhotoId());
	}

	@Test
	public void getPhotos(){
		List photos = photoService.getPhotos();
		for (Object o : photos){
			Photo photo = (Photo) o;
			System.out.println(photo.getPhotoId());
		}
	}

	@Test
	public void updatePhoto(){
		Photo photo = photoService.getPhoto(207);
		photo.setPhotoName("更新图片");
		photoService.updatePhoto(photo);
	}

	@Test
	public void deletePhoto(){
		Photo photo = photoService.getPhoto(208);
		boolean a = photoService.deletePhoto(photo);
		System.out.println(a);
	}
}















