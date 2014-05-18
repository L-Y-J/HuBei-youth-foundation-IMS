package unit;

import bean.Job;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.JobService;

import java.util.List;

/**
 * Created by yongjie on 14-5-18.
 */
public class JobTest {

	private static JobService jobService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			jobService = (JobService) applicationcontext.getBean("JobService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addJobTest(){
		Job job = new Job();
		job.setJobName("秘书");
		boolean a = jobService.addJob(job);
		System.out.println(a);
	}

	@Test
	public void getJobTest(){
		Job job = jobService.getJob(122);
		System.out.println(job.getJobName());
	}

	@Test
	public void getJobsTest(){
		List jobs = jobService.getJobs();
		for (Object o : jobs){
			Job job = (Job) o;
			System.out.println(job.getJobName());
		}
	}

	@Test
	public void updateJobTest(){
		Job job = jobService.getJob(122);
		job.setJobName("书记");
		jobService.updateJob(job);
	}

	@Test
	public void deleteJobTest(){
		Job job = jobService.getJob(122);
		boolean a = jobService.deleteJob(job);
		System.out.println(a);
	}
}
