package ds.assign1.messaging.repos;

import ds.assign1.messaging.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeasurementRepo extends JpaRepository<Measurement, Long> {
}
