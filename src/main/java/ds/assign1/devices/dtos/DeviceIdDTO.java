package ds.assign1.devices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeviceIdDTO {
    private UUID id;
}
