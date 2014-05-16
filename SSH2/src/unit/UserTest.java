package unit;

import bean.Power;
import bean.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.PowerService;
import service.UserService;

import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-5-12.
 */
public class UserTest {

	public static UserService userService;
	public static PowerService powerService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			userService = (UserService) applicationcontext.getBean("UserService");
			powerService = (PowerService) applicationcontext.getBean("PowerService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addUserTest(){
		User user = new User();
		user.setUserName("text");
		user.setName("li");
		user.setLeval("省级用户");
		user.setPassword("111222");
		user.setInnerId("12344556");
		boolean tag = userService.addUser(user);
		System.out.println(tag);
	}

	@Test
	public void getUserTest(){
		User user = userService.getUser(35);
		System.out.println(user.getUserName());
	}

	@Test
	public void deleteUserTest(){
		User user = userService.getUser(65);
		boolean b = userService.deleteUser(user);
		System.out.println(b);
	}

	@Test
	public void updateUserTest(){
		User user = userService.getUser(35);
		user.setUserName("wbswsb");
		userService.updateUser(user);
	}

	@Test
	public void getUsersTest(){
		List list = userService.getUsers();
		for (Object l : list){
			User user = (User) l;
			System.out.println(user.getUserName());
		}
	}

	@Test
	public void getUserPower(){
		User user = userService.getUser(26);
		Set powers = user.getPower();
		for (Object o : powers){
			Power power = (Power) o;
			System.out.println(power.getId());
		}
	}

	@Test
	public void User_Power_Test(){
		User user = userService.getUser(27);
		Power power = powerService.getPower(28);
		user.addPower(power);
		powerService.updatePower(power);  //更新power有用，更新user无用
	}

	@Test
	public void User_Role_Test(){         //用户可以自己更改角色，但角色只能添加用户，无法删除
		User user = userService.getUser(35);
		user.setRoleId(null);
		userService.updateUser(user);
	}

	@Test
	public void queryNameAndPasswordTest(){
		List list = userService.queryNameAndPassword("xcz", "222222");
		for (Object l : list){
			User user = (User) l;
			System.out.println(user.getUserName());
		}
		System.out.println(list.size());
	}
}
