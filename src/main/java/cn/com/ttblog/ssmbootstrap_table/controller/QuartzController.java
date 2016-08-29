package cn.com.ttblog.ssmbootstrap_table.controller;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
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

import cn.com.ttblog.ssmbootstrap_table.model.HelloJob;

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
		try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		List<QuartzJobBean> jobList = new ArrayList<QuartzJobBean>();
		return jobList;
	}

	@RequestMapping("/addjob")
	private Object addjob() {
		logger.debug("add job");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").storeDurably().build();
		Date runTime = new DateTime().plusMillis(2).toDate();
		// Trigger the job to run on the next round minute
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		try {
//			scheduler.scheduleJob(job, trigger);
			scheduler.addJob(job, true);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("添加定时任务出错");
		}
		return job;
	}

}