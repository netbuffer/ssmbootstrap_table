//package cn.com.ttblog.ssmbootstrap_table.task;
//
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.math.RandomUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * springtask定时任务测试
// * 
// * @author netbuffer
// */
//@Component
//public class Task {
//
//	Logger log = LoggerFactory.getLogger(Task.class);
//
//	@Scheduled(cron = "0 */1 * * * ?")
//	public void testSchedule() {
//		int sleep=RandomUtils.nextInt(100);
//		log.debug("触发定时任务{}，sleep:{}s",this,sleep);
//		try {
//			TimeUnit.SECONDS.sleep(sleep);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public String toString(){
//		return ToStringBuilder.reflectionToString(this);
//	}
//}
