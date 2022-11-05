package ds.assign1.devices.repos;

import ds.assign1.devices.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevicesRepo extends JpaRepository<Device, UUID> {
}
