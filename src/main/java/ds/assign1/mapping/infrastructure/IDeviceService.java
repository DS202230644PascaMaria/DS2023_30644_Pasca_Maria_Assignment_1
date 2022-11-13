package ds.assign1.mapping.infrastructure;

import ds.assign1.devices.dtos.DeviceDTO;

import java.util.UUID;

public interface IDeviceService {
    DeviceDTO findDeviceById(UUID idToSearch);
    void addOwner(UUID accountId, UUID deviceId);
    void removeOwner(UUID deviceId);
    UUID getOwner(UUID deviceId);
}
