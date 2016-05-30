package me.ahmed.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HeaderFooterLuncher {

	@SuppressWarnings("resource")
	public static void main(String areg[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-header-footer.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher1");
		Job job = (Job) context.getBean("userJob1");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Header / footer Exit Status : " + execution.getStatus());

		} catch (JobExecutionException e) {
			System.out.println("Job Header / footer  User failed");
			e.printStackTrace();
		}
	}

}