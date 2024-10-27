package distance.com.example.distance;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private WebSocketSession esp32Session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.esp32Session = session;
    }

    public void sendMessageToEsp32(String message) throws Exception {
        if (esp32Session != null && esp32Session.isOpen()) {
            esp32Session.sendMessage(new TextMessage(message));
        }
    }
}