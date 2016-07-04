package cn.com.ttblog.ssmbootstrap_table.listener;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import cn.com.ttblog.ssmbootstrap_table.event.LoginEvent;

@Component  
public class TestReceiveLoginEventListener1 implements SmartApplicationListener {  
  
	private static final Logger log = LoggerFactory.getLogger(TestReceiveLoginEventListener2.class);

	@Override
	public boolean supportsEventType(final Class<? extends ApplicationEvent> eventType) {
		return eventType == LoginEvent.class;
	}

	@Override
	public boolean supportsSourceType(final Class<?> sourceType) {
		//这里传具体的类才生效，测试使用Map.class无效
		return sourceType == HashMap.class;
	}

	@Override
	public void onApplicationEvent(final ApplicationEvent event) {
		log.info("{}收到loginevent:{}" ,this.getClass().getName(), event.getSource());
	}
	
    @Override  
    public int getOrder() {  
        return 1;  
    }  
}  