package cn.com.ttblog.ssmbootstrap_table.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import cn.com.ttblog.ssmbootstrap_table.event.LoginEvent;

@Component
public class LoginContextListener implements ApplicationListener<ApplicationEvent> {
	
	private static final Logger log=LoggerFactory.getLogger(LoginContextListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationEvent e) {
//		http://jinnianshilongnian.iteye.com/blog/1902886
		if (e instanceof ContextStartedEvent) {
			log.warn("it was contextStartedEvent!");
		}

		if (e instanceof LoginEvent) {
			log.warn("LoginEvent事件响应:{}",e.getSource());
		}

	}

}