package unit;

import bean.Department;
import bean.Job;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DepartmentService;
import service.JobService;

import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-5-17.
 */
public class DepartmentTest {

	private static DepartmentService departmentService;
	private static JobService jobService;

    @BeforeClass
	 public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			departmentService = (DepartmentService) applicationcontext.getBean("DepartmentService");
			jobService = (JobService) applicationcontext.getBean("JobService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addDepartmentTest(){
		Department department = new Department();
		department.setDepartmentName("人事部");
		department.setLeval("省级部门");
		boolean a = departmentService.addDepartment(department);
		System.out.println(a);
	}

	@Test
	public void getDepartmentTest(){
		Department department = departmentService.getDepartment(101);
		System.out.println(department.getDepartmentName());

	}

	@Test
	public void getDepartmentsTest(){
		List departments = departmentService.getDepartments();
		for (Object o : departments){
			Department department = (Department) o;
			System.out.println(department.getDepartmentName());
		}
	}

	@Test
	public void updateDepartmentTest(){
		Department department = departmentService.getDepartment(121);
		department.setLeval("省级部门");
		boolean a = departmentService.updateDepartment(department);
		System.out.println(a);
	}

	@Test
	public void deleteDepartmentTest(){
		Department department = departmentService.getDepartment(101);
		boolean a = departmentService.deleteDepartment(department);
		System.out.println(a);
	}

	@Test
	public void Department_Job_Test(){
		Department department = departmentService.getDepartment(121);
//		Job job = jobService.getJob(123);
//		department.getJob().add(job);
//		departmentService.updateDepartment(department);
		Set set = department.getJob();
		for (Object o : set){
			Job job = (Job) o;
			job.setDepartmentId(null);
			jobService.updateJob(job);
		}
	}

}

















