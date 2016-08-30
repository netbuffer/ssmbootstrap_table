package cn.com.ttblog.ssmbootstrap_table.controller;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.ttblog.ssmbootstrap_table.task.job.HelloJob;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

	private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@RequestMapping("/list")
	private Object list() {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		Set<JobKey> jobKeys=null;
		try {
			 jobKeys= scheduler.getJobKeys(matcher);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		logger.debug("查询job-keys:{}",jobKeys);
		List<JobDetail> jobList = new ArrayList<JobDetail>();
		if(jobKeys!=null&&jobKeys.size()>0){
			for(JobKey j:jobKeys){
				try {
					jobList.add(scheduler.getJobDetail(j));
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("查询job-list:{}",jobList);
		return jobList;
	}

	@RequestMapping("/addjob")
	private Object addjob() {
		logger.debug("add job");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jk=new JobKey("job1", "group1");
		JobDetail job = newJob(HelloJob.class).withIdentity(jk).storeDurably().build();
		Date runTime = new DateTime().plusMillis(2).toDate();
		// Trigger the job to run on the next round minute
//		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").forJob(job).startAt(runTime).build();
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").forJob(job).withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
		try {
			if(scheduler.checkExists(new JobKey("job1","group1"))){
				logger.debug("任务:{}已存在,删除处理!",jk);
				scheduler.deleteJob(jk);
			}
			scheduler.scheduleJob(job, trigger);
//			scheduler.addJob(job, true);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("添加定时任务出错:"+e.getMessage());
		}
		return job;
	}
	
	@RequestMapping("/deljob")
	private Object deljob() {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey key=new JobKey("", "");
		logger.debug("删除任务:{}",key);
		try {
			scheduler.deleteJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return key;
	}

}