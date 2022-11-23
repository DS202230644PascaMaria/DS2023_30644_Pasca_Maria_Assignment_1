package ds.assign1.mapping;

import ds.assign1.devices.dtos.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/mapping")
@RequiredArgsConstructor
public class MappingController {
    private final MappingService SERVICE;

    @GetMapping("/{id}")
    public List<DeviceDTO> getOwnedDevices(@PathVariable("id") UUID accountId){
        return SERVICE.getOwnedDevices(accountId);
    }

    @GetMapping("/mappings")
    public Map<UUID, List<DeviceDTO>> getMappings(){
        return SERVICE.getMappings();
    }

    @PutMapping("/insert/{id}")
    public void insertDevice(@PathVariable("id") UUID accountId, @RequestBody MappingDTO dto){
        SERVICE.insertDevice(accountId, dto.getDeviceId());
    }

    @PutMapping("/remove/{id}")
    public void removeDevice(@PathVariable("id") UUID accountId, @RequestBody MappingDTO mappingDTO){
        SERVICE.removeDevice(accountId, mappingDTO.getDeviceId());
    }
}
