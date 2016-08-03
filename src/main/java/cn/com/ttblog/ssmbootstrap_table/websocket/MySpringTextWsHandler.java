package cn.com.ttblog.ssmbootstrap_table.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MySpringTextWsHandler extends TextWebSocketHandler
{

    /** 
     * 方法用途: <br>
     * 实现步骤: <br>
     * @param session
     * @param message
     * @throws Exception   
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        if(session.isOpen())
        {
            session.sendMessage(message);
        }
    }

    /** 
     * 方法用途: <br>
     * 实现步骤: <br>
     * @param session
     * @throws Exception   
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        super.afterConnectionEstablished(session);
//        SpringWebsocketConstant.map.put("1", session);
    }

}