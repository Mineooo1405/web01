package distance.com.example.distance;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Distance")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DistanceController {
    DistanceRepository DistanceRepository;

    @GetMapping("/GetDistance")
    public List<Distance> GetDistance(){
        return DistanceRepository.findAll();
    }
}
