package cn.com.ttblog.ssmbootstrap_table.task.job;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import cn.com.ttblog.ssmbootstrap_table.service.IUserService;

/**
 * Note the '@DisallowConcurrentExecution' annotation that makes sure that only a single instance of a job will ever run at a given time.
 * http://www.cnblogs.com/tomcattd/p/3512811.html
 */
//@DisallowConcurrentExecution
public class HelloJob extends QuartzJobBean{

	private static final Logger LOG=LoggerFactory.getLogger(HelloJob.class);
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		LOG.debug("[HelloJob] job execute!context:{},jobdata:{}",context,context.getJobDetail().getJobDataMap());
//		ApplicationContext springContext=(ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContextKey");
//		LOG.debug("job get spring context:{},get bean：{}",springContext,springContext.getBean("config"));
//	}
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOG.debug("[HelloJob] job execute!context:{},jobdata:{}",context,context.getJobDetail().getJobDataMap());
		SchedulerContext sc=null;
		ApplicationContext springContext=null;
        try {
        	sc= context.getScheduler().getContext();
			springContext=(ApplicationContext) sc.get("applicationContextKey");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		LOG.debug("jobget scheduler context:{},spring context:{},get bean：{}",sc,springContext,springContext.getBean("config"));
		IUserService userService=springContext.getBean("userService",IUserService.class);
		LOG.debug("quartz job,userService.getUserListCount():{}",userService.getUserListCount());
		try {
			LOG.debug("写入任务文件!");
			String instanceId=context.getScheduler().getSchedulerInstanceId();
			FileUtils.writeStringToFile(new File("d:/task.txt"),"["+this.getClass().getResource("").getFile()+"]"+"instanceId:["+instanceId
					+"]"+new DateTime().toString("yyyy-MM-dd HH:mm:ss")+Thread.currentThread().getName()+"定时任务hello执行\r\n",true);
		} catch (IOException |SchedulerException se) {
			se.printStackTrace();
		}
	}
}