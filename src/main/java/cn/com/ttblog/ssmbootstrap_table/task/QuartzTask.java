package cn.com.ttblog.ssmbootstrap_table.task;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//http://nesuk.iteye.com/blog/1582557
public class QuartzTask {
	
	public static final Logger log=LoggerFactory.getLogger(QuartzTask.class);

	private void run() {
		int sleep=RandomUtils.nextInt(100);
		log.debug("触发定时任务{}，sleep:{}s",this,sleep);
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}