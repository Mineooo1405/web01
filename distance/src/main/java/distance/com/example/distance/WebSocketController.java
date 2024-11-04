package distance.com.example.distance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class WebSocketController {
    @Autowired
    private MyWebSocketHandler webSocketHandler;

    @PostMapping("/trigger")
    public String triggerCalculation() throws Exception {
        webSocketHandler.sendMessageToEsp32("Start calculation");
        return "Message sent to ESP32";
    }

    @PostMapping("/storeData")
    public String storeData(@RequestParam("file")MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Float> dataList = mapper.readValue(file.getInputStream(), new TypeReference<List<Float>>() {});
        return "Successfully stored data";
    }

}