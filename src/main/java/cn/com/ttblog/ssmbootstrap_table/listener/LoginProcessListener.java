package cn.com.ttblog.ssmbootstrap_table.listener;

import cn.com.ttblog.ssmbootstrap_table.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class LoginProcessListener implements ApplicationListener<LoginEvent> {
	
	private static final Logger LOG=LoggerFactory.getLogger(LoginProcessListener.class);

	/**
	 * @Async异步处理
	 */
	@Async
	@Override
	public void onApplicationEvent(LoginEvent loginEvent) {
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			LOG.error("sleep error",e);
		}
		LOG.debug("异步记录用户登录日志:{}",loginEvent);
	}
}