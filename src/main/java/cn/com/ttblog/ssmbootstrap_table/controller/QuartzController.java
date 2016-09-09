package cn.com.ttblog.ssmbootstrap_table.controller;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
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
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * 添加定时任务
	 * @param className 定时任务类名
	 * @param jobName 任务名
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/addjob/{class}/{jobName}")
	private Object addjob(@PathVariable("class") String className,@PathVariable("jobName") String jobName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		logger.debug("add job");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		className="cn.com.ttblog.ssmbootstrap_table.task.job."+className;
		logger.debug("实例化类名:{}",className);
		JobKey jk=new JobKey(jobName, "group1");
//		Class.forName(className).newInstance().getClass()
		JobDetail job = newJob(((Job)(Class.forName(className).newInstance())).getClass()).withIdentity(jk).storeDurably().build();
		Date runTime = new DateTime().plusMillis(2).toDate();
		// Trigger the job to run on the next round minute
//		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").forJob(job).startAt(runTime).build();
		Trigger trigger = newTrigger().withIdentity(jobName, "group1").forJob(job).withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
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
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param group
	 * @return
	 */
	@RequestMapping("/deljob/{jobName}/{group}")
	private Object deljob(@PathVariable("jobName") String jobName,@PathVariable("group") String group) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey key=new JobKey(jobName,group);
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