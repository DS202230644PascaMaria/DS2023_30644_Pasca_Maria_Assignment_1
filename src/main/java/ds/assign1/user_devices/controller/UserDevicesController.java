package ds.assign1.user_devices.controller;

import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.user_devices.dtos.OutUserDevicesDTO;
import ds.assign1.user_devices.dtos.UserDeviceDTO;
import ds.assign1.user_devices.services.UserDevicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mapping")
public class UserDevicesController {
    private final UserDevicesService SERVICE;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUserDevicesList(@RequestBody UserDeviceDTO userDeviceDTO){
        return SERVICE.createUserDeviceList(userDeviceDTO);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<OutUserDevicesDTO> findAll(){
        return SERVICE.showAll();
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<DeviceDTO> findDevicesOwnedByAccount(@PathVariable("id") UUID accountId){
        return SERVICE.findDevicesOwnedById(accountId);
    }

    @PostMapping("/insert/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID insertDevice(@PathVariable("id") UUID accountId, @RequestBody UUID deviceId){
        return SERVICE.insertDevice(accountId, deviceId);
    }

    @PostMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public UUID removeDevice(@PathVariable("id") UUID accountId, @RequestBody UUID deviceId){
        return SERVICE.removeDevice(accountId, deviceId);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public UUID deleteUserDevices(@PathVariable("id") UUID accountId){
        return SERVICE.deleteUserDevices(accountId);
    }
}
