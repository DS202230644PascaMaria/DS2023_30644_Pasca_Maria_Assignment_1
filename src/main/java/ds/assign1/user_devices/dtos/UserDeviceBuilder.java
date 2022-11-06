package ds.assign1.user_devices.dtos;

import ds.assign1.user_devices.entities.UserDevices;

import java.util.List;
import java.util.UUID;

public class UserDeviceBuilder {

    public static UserDeviceDTO build(UserDevices userDevices){
        return new UserDeviceDTO(userDevices.getId(), userDevices.getAccountId(), userDevices.getDeviceIdList());
    }

    public static UserDevices build(UUID id, UserDeviceDTO dto){
        return new UserDevices(id, dto.getAccountId(), dto.getDevicesId());
    }

    public static UserDevices build(UserDeviceDTO dto){
        return new UserDevices(dto.getAccountId(), dto.getDevicesId());
    }
}
