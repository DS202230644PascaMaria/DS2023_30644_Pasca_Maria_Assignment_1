package ds.assign1.user_devices.infrastructure;

import ds.assign1.devices.dtos.DeviceDTO;

import java.util.UUID;

public interface IDeviceService {
    DeviceDTO findDeviceById(UUID idToSearch);
}
