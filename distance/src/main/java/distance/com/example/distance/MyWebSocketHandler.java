package distance.com.example.distance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.micrometer.common.lang.NonNull;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyWebSocketHandler extends TextWebSocketHandler {
    private  WebSocketSession esp32Session;
    private final DistanceRepository distanceRepository;

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        this.esp32Session = session;
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
        // Nếu cần, xử lý tín hiệu tại đây
        session.sendMessage(new TextMessage("Message received: " + message.getPayload()));
    }

    public void sendMessageToEsp32(String message) throws Exception {
        if (esp32Session != null && esp32Session.isOpen()) {
            esp32Session.sendMessage(new TextMessage(message));
        }
    }

    public void storeDistance(List<Float> distances) {
        for(int i = distances.size() - 1; i >= 0; i--) {
            Distance storedData = new Distance(distances.get(i));
            distanceRepository.save(storedData);
        }
    }
}