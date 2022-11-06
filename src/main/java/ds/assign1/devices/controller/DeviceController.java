package ds.assign1.devices.controller;

import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.devices.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addDevices(@RequestBody DeviceDTO dto){
        return deviceService.addDevice(dto);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceDTO> getAllDevices(){
        return deviceService.findDevices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public DeviceDTO getDeviceById(@PathVariable("id") UUID id){
        return deviceService.findDeviceById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DeviceDTO updateDevice(@PathVariable("id") UUID id, @RequestBody DeviceDTO dto){
        return deviceService.updateDevice(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public UUID deleteDevice(@PathVariable("id") UUID id){
        return deviceService.deleteDevice(id);
    }
}
