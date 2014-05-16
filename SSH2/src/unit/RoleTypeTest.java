package unit;

import bean.Role;
import bean.RoleType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.RoleService;
import service.RoleTypeService;

import java.util.List;

/**
 * Created by yongjie on 14-5-13.
 */
public class RoleTypeTest {

	public static RoleTypeService roleTypeService;
	public static RoleService roleService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			roleTypeService = (RoleTypeService) applicationcontext.getBean("RoleTypeService");
			roleService = (RoleService) applicationcontext.getBean("RoleService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getRoleTypeTest(){
		RoleType roleType = roleTypeService.getRoleType(30);
		System.out.println(roleType.getTypeName());
	}

	@Test
	public void getRoleTypesTest(){
		List roleTypes = roleTypeService.getRoleTypes();
		for (Object o : roleTypes){
			RoleType roleType = (RoleType) o;
			System.out.println(roleType.getTypeName());
		}
	}

	@Test
	public void addRoleTypeTest(){
		RoleType roleType = new RoleType();
		roleType.setTypeName("系统角色");
		boolean b = roleTypeService.addRoleType(roleType);
		System.out.println(b);
	}

	@Test
	public void updateRoleTypeTest(){
		RoleType roleType = roleTypeService.getRoleType(30);
		roleType.setTypeName("普通角色");
		boolean b = roleTypeService.updateRoleType(roleType);
		System.out.println(b);
	}

	@Test
	public void deleteRoleTypeTest(){
		RoleType roleType = roleTypeService.getRoleType(32);
		boolean b = roleTypeService.deleteRoleType(roleType);
		System.out.println(b);
	}

	@Test
	public void Role_RoleType_Test(){
		Role role = roleService.getRole(34);
		RoleType roleType = roleTypeService.getRoleType(30);
		roleType.addRole(role);
		roleTypeService.updateRoleType(roleType);
		roleService.updateRole(role);

//		Role role = roleService.getRole(25);              //角色类别删除角色成功
//		RoleType roleType = roleTypeService.getRoleType(30);
//		roleType.deleteRole(role);
//		roleService.updateRole(role);
//		roleTypeService.updateRoleType(roleType);
	}

	@Test
	public void getRoleTypeByName_Test(){
		List list = roleTypeService.getRoleTypeByName("系统角色");
		System.out.println(list.size());
		System.out.println(((RoleType)list.get(0)).getTypeName());
	}

}
