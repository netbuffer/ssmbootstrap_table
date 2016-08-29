package cn.com.ttblog.ssmbootstrap_table.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job{

	private static final Logger LOG=LoggerFactory.getLogger(HelloJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.debug("job execute:{}",context);
	}
	
} 
 