package ds.assign1.user_devices.repo;

import ds.assign1.user_devices.entities.UserDevices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDevicesRepo extends JpaRepository<UserDevices, UUID> {
    Optional<UserDevices> findByAccountId(UUID accountId);
}
