package ds.assign1.messaging.infrastructure;

import ds.assign1.devices.dtos.DeviceDTO;

import java.util.UUID;

public interface IDeviceMeasurement {
    public DeviceDTO findDeviceById(UUID idToSearch);
}
