package distance.com.example.distance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Distance {
    @Id
    @Column(name = "Distance", nullable = false)
    private Double id;

    //TODO [Reverse Engineering] generate columns from DB
}