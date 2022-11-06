package ds.assign1.devices.dtos.builders;

import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.devices.entities.Device;

import java.util.UUID;

public class DeviceBuilder {
    public static DeviceDTO build(Device entity){
        return new DeviceDTO(entity.getId(), entity.getDescription(), entity.getAddress(), entity.getMaxHourlyConsumption());
    }

    public static Device build(DeviceDTO dto){
        return new Device(dto.getDescription(), dto.getAddress(), dto.getMaxHourlyConsumption());
    }

    public static Device build(UUID id, DeviceDTO dto){
        return new Device(id, dto.getDescription(), dto.getAddress(), dto.getMaxHourlyConsumption());
    }
}
