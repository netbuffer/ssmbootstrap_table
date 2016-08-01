package cn.com.ttblog.ssmbootstrap_table;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycila.junit.concurrent.Concurrency;
import com.mycila.junit.concurrent.ConcurrentJunitRunner;
//http://darrendu.iteye.com/blog/765733 关于junit中测试多线程疑问
// http://my.oschina.net/ydsakyclguozi/blog/400691 junit+GroboUtils 多线程测试
//http://www.whiteboxtest.com/JUnit4-Multithreading-And-Concurrency-Testing.php
public class TestSynchronized {
	public static final Logger log=LoggerFactory.getLogger(TestSynchronized.class);
	@Test
//	@Ignore
	public void test(){
		log.debug("test1 start");
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					new Demo().run();
				}
			}).start();
		}
	}
	
	@Test
	@Ignore
	public void test2(){
		log.debug("test2 start");
		final Demo d=new Demo();
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					d.run();
				}
			}).start();
		}
	}
	
}
class Demo{
	public Demo(){
		System.out.println("init demo");
	}
	//其它访问该对象的线程将被阻塞
	public synchronized void run(){
		final Logger log=LoggerFactory.getLogger(Demo.class);
		int sleep=RandomUtils.nextInt(100);
		log.debug("execute,sleep:{}s",sleep);
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
