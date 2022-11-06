package ds.assign1.user_devices.dtos;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.devices.dtos.DeviceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OutUserDevicesDTO {
    UUID id;
    AccountDTO accountDTO;
    List<DeviceDTO> deviceDTOList;
}
