package unit;

import bean.Department;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DepartmentService;

/**
 * Created by yongjie on 14-5-17.
 */
public class DepartmentTest {

	private static DepartmentService departmentService;

    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			departmentService = (DepartmentService) applicationcontext.getBean("DepartmentService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addDepartmentTest(){
		Department department = new Department();
		department.setDepartmentName("后勤部");
		department.setLeval("省级部门");
		boolean a = departmentService.addDepartment(department);
		System.out.println(a);
	}

	@Test
	public void getDepartmentTest(){
		Department department = departmentService.getDepartment(101);
		System.out.println(department.getDepartmentName());

	}

}

















