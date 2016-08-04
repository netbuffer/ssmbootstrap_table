package cn.com.ttblog.ssmbootstrap_table.websocket;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MySpringTextWsHandler extends TextWebSocketHandler {

	private static final Logger log=LoggerFactory.getLogger(MySpringTextWsHandler.class);
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if (session.isOpen()) {
			log.warn("receive websocket message：{} session:{}",ToStringBuilder.reflectionToString(message),ToStringBuilder.reflectionToString(session));
			TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
			session.sendMessage(returnMessage);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		// SpringWebsocketConstant.map.put("1", session);
	}
}