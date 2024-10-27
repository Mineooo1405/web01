package distance.com.example.distance;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceRepository extends JpaRepository<Distance, Instant> {
}