package unit;

import bean.Power;
import bean.Role;
import bean.RoleType;
import bean.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.PowerService;
import service.RoleService;
import service.RoleTypeService;
import service.UserService;

import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-5-13.
 */
public class RoleTest {

	public static UserService userService;
	public static PowerService powerService;
	public static RoleService roleService;
	public static RoleTypeService roleTypeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			userService = (UserService) applicationcontext.getBean("UserService");
			powerService = (PowerService) applicationcontext.getBean("PowerService");
			roleService = (RoleService) applicationcontext.getBean("RoleService");
			roleTypeService = (RoleTypeService) applicationcontext.getBean("RoleTypeService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getRoleTest(){
		Role role = roleService.getRole(34);
		if (role==null)
			System.out.println("error");
		System.out.println(role.getRoleName());
	}

	@Test
	public void getRolesTest(){
		List roles = roleService.getRoles();
		for (Object l : roles){
			Role role = (Role) l;
			System.out.println(role.getRoleName());
		}
	}

	@Test
	public void addRoleTest(){
		Role role = new Role();
		role.setRoleName("普通用户");
		boolean b = roleService.addRole(role);
		System.out.println(b);

	}

	@Test
	public void updateRoleTest(){
		Role role = roleService.getRole(41);
		role.setRoleName("一般用户");
		boolean b = roleService.updateRole(role);
		System.out.println(b);
	}

	@Test
	public void deleteRoleTest(){
		Role role = roleService.getRole(64);
		boolean b = roleService.deleteRole(role);
		System.out.println(b);

	}

	@Test
	public void Role_User_Test(){
		Role role = roleService.getRole(34);

//		Set user = role.getUser();
//		for (Object u : user){
//			User us = (User) u;
//			System.out.println(us.getUserName());
//		}

		User user1 = userService.getUser(35);   //角色添加用户成功
		role.addUser(user1);
		roleService.updateRole(role);

	}

	@Test
	public void Role_Power_Test(){
		Role role = roleService.getRole(34);
		Power power = powerService.getPower(29);  //角色添加权限，
		role.addPower(power);
		//roleService.updateRole(role);           //更新角色失败
		powerService.updatePower(power);          //更新权限成功
	}

	@Test
	public void Role_RoleType_Test(){
		Role role = roleService.getRole(25);                 //角色添加角色类别成功
		RoleType roleType = roleTypeService.getRoleType(42);
//		role.addRoleType(roleType);
//		roleService.updateRole(role);
//		roleTypeService.updateRoleType(roleType);

//		Role role = roleService.getRole(25);
//		RoleType roleType = roleTypeService.getRoleType(30);
//		Set set = role.getRoleType();
//		for (Object o : set){
//			RoleType type = (RoleType) o;
//			System.out.println(type.getTypeName());
//		}
//
//		System.out.println("-----------------");
//		role.deleteRoleType(roleType);
//		System.out.println(roleType.getTypeName());
//		System.out.println("-----------------");
//
//
//		set = role.getRoleType();
//		for (Object o : set){
//			RoleType type = (RoleType) o;
//			System.out.println(type.getTypeName());
//		}

		role.deleteRoleType(roleType);              //角色类别删除成功
		roleService.updateRole(role);
		roleTypeService.updateRoleType(roleType);
	}

	@Test
	public void getRoleByName_Test(){
		Role role = roleService.getRoleByName("管理员");
		System.out.println(role.getId());
	}






}





















