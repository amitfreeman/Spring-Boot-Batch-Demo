package com.javainuse.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.javainuse.listener.JobCompletionListener;
import com.javainuse.step.Processor;
import com.javainuse.step.Reader;
import com.javainuse.step.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job processJob() {
		System.out.println("#AMIT, in process job, creating file");
		
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		System.out.println("#AMIT, in orderStep1");
		return stepBuilderFactory.get("orderStep1").<String, String> chunk(5)
				.reader( new Reader()).processor(new Processor())
				.writer( new Writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		System.out.println("#AMIT in listner");
		return new JobCompletionListener();
	}
	
	@Bean
	public Job runJob() {
		System.out.println("#AMIT in runJob");
		
		
		return this.jobBuilderFactory.get("runJob")
				.start(orderStep1())
				.build();
	}
	
    /*@Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job job;
	
    //@Scheduled(cron = "0 *//*1 * * * ?")
    /*public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }*/

}