package unit;

import bean.Power;
import bean.Role;
import bean.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.PowerService;
import service.RoleService;
import service.UserService;

import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-5-13.
 */
public class PowerTest {

	public static UserService userService;
	public static PowerService powerService;
	public static RoleService roleService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			userService = (UserService) applicationcontext.getBean("UserService");
			powerService = (PowerService) applicationcontext.getBean("PowerService");
			roleService = (RoleService) applicationcontext.getBean("RoleService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getPowerTest(){
		Power power = powerService.getPower(28);
		System.out.println(power.getPowerName());
	}

	@Test
	public void getPowersTest(){
		List list = powerService.getPowers();
		for (Object l : list){
			Power power = (Power) l;
			System.out.println(power.getPowerName());
		}
	}

	@Test
	public void addPowerTest(){
		Power power = new Power();
		power.setPowerName("测试");
		Boolean tag = powerService.addPower(power);
		System.out.println(tag);
	}

	@Test
	public void deletePowerTest(){
		Power power = powerService.getPower(37);
		boolean b = powerService.deletePower(power);
		System.out.println(b);
	}

	@Test
	public void updatePowerTest(){
		Power power = powerService.getPower(28);
		power.setPowerName("导出");
		powerService.updatePower(power);
	}

	@Test
	public void Power_User_Test(){
		Power power = powerService.getPower(33);
		User user = userService.getUser(26);
		power.addUser(user);
		powerService.updatePower(power);
	}

	@Test
	public void Power_Role_Test(){
		Power power = powerService.getPower(28);

//		Set role = power.getRole();
//		for (Object o : role){
//			Role role1 = (Role) o;
//			System.out.println(role1.getRoleName());  //权限属于哪些角色
//		}

//		Role role1 = roleService.getRole(34);         //权限添加角色成功
//		power.addRole(role1);
//		powerService.updatePower(power);

		Role role = roleService.getRole(34);          //权限删除角色失败
		power.getRole().remove(role);
		powerService.updatePower(power);
	}

	@Test
	public void getPowerByName_Test(){
		Power power = powerService.getPowerByName("查看");
		System.out.println(power.getId());
	}

}












