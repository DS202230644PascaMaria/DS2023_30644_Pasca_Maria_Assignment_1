package ds.assign1.messaging.infrastructure;

import ds.assign1.devices.dtos.DeviceDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IDeviceMeasurement {
    public DeviceDTO findDeviceById(UUID idToSearch);
}
