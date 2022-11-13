package ds.assign1.devices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeviceDTO {
    UUID id;
    String description;
    String address;
    float maxHourlyConsumption;
    UUID pairedAccountId;
}
