package ds.assign1.user_devices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDeviceDTO {
    UUID id;
    UUID accountId;
    List<UUID> devicesId;
}
