package distance.com.example.distance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Distance{ 
    @Id
    @Column(name = "Date", nullable = false)
    private Instant id;

    @Column(name = "Distance")
    private Double Distance;
}