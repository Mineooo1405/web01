package distance.com.example.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @Autowired
    private MyWebSocketHandler webSocketHandler;

    @PostMapping("/trigger")
    public String triggerCalculation() throws Exception {
        webSocketHandler.sendMessageToEsp32("Start calculation");
        return "Message sent to ESP32";
    }
}