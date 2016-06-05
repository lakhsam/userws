package me.ahmed.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HeaderFooterLuncher {

	private static final Logger log = Logger
			.getLogger(HeaderFooterLuncher.class);
	private static ClassPathXmlApplicationContext context;

	public static void main(String areg[]) {
		context = new ClassPathXmlApplicationContext(
				"spring-batch-header-footer.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher1");
		Job job = (Job) context.getBean("userJob1");

		try {
			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			jobParametersBuilder.addString("inputDataFile",areg[0]);
			jobParametersBuilder.addString("codeBanque","23890");
			JobParameters param = jobParametersBuilder.toJobParameters();
			JobExecution execution = jobLauncher.run(job, param);
			System.out.println("Job Header / footer Exit Status : "
					+ execution.getStatus());

		} catch (JobExecutionException e) {
			System.out.println("Job Header / footer  User failed");
			log.info(e.getStackTrace());
		} finally {
			if (context != null)
				context.close();
		}

		log.info("Job Finished");
	}

}