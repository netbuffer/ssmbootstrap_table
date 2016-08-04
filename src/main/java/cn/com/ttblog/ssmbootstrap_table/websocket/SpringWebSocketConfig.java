package cn.com.ttblog.ssmbootstrap_table.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myWsHandler(), "/ws");
		registry.addHandler(myWsHandler(), "/wsockjs").withSockJS();
	}

	@Bean
	public WebSocketHandler myWsHandler() {
		return new MySpringTextWsHandler();
	}

	// Allow serving HTML files through the default Servlet
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}