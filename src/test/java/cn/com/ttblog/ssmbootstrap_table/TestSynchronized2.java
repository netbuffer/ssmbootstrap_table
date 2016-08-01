package cn.com.ttblog.ssmbootstrap_table;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TestSynchronized2 {
	public static final Logger log=LoggerFactory.getLogger(TestSynchronized2.class);
	@Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
	public void test(){
		log.debug("test1 start");
		new Demo2().run();
	}
	
	@Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
	public void test2(){
		log.debug("test2 start");
		final Demo2 d=new Demo2();
		d.run();
	}
	
}
class Demo2{
	public Demo2(){
		System.out.println("init demo");
	}
	//其它访问该对象的线程将被阻塞
	public synchronized void run(){
		final Logger log=LoggerFactory.getLogger(Demo2.class);
		int sleep=RandomUtils.nextInt(100);
		log.debug("{}-execute,sleep:{}s",Thread.currentThread().toString(),sleep);
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
