package cn.com.ttblog.ssmbootstrap_table.task.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import cn.com.ttblog.ssmbootstrap_table.model.User;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

@DisallowConcurrentExecution
public class TransactionJob extends QuartzJobBean{

	private static final Logger LOG=LoggerFactory.getLogger(TransactionJob.class);
	
//	@Transactional
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOG.debug("[TransactionJob] execute:{}",context);
		SchedulerContext sc=null;
		ApplicationContext springContext=null;
        try {
        	sc= context.getScheduler().getContext();
			springContext=(ApplicationContext) sc.get("applicationContextKey");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		IUserService userService=springContext.getBean("userService",IUserService.class);
		userService.addUser(new User("test"));
		throw new RuntimeException("add user error");
	}
}