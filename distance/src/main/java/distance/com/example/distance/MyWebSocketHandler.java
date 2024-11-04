package distance.com.example.distance;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class MyWebSocketHandler extends TextWebSocketHandler {
    private WebSocketSession esp32Session;
    private final DistanceRepository distanceRepository;

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