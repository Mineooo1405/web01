package distance.com.example.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignalController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/Distance/sendSignal")
    public String sendSignal(@RequestBody SignalRequest request) {
        // Gửi tín hiệu đến tất cả các client đang lắng nghe
        messagingTemplate.convertAndSend("/topic/messages", request.getSignal());
        return "Signal sent: " + request.getSignal();
    }

    public static class SignalRequest {
        private String signal;

        public String getSignal() {
            return signal;
        }

        public void setSignal(String signal) {
            this.signal = signal;
        }
    }
}