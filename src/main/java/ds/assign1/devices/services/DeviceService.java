package ds.assign1.devices.services;

import ds.assign1.accounts.entities.Account;
import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.devices.dtos.builders.DeviceBuilder;
import ds.assign1.devices.entities.Device;
import ds.assign1.devices.repos.DevicesRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    private final DevicesRepo devicesRepo;

    // Create
    public UUID addDevice(DeviceDTO dto){
        Device newDevice = DeviceBuilder.build(dto);

        newDevice = devicesRepo.save(newDevice);
        return newDevice.getId();
    }

    // Read
    public List<DeviceDTO> findDevices(){
        List<Device> allDevices = devicesRepo.findAll();

        return allDevices.stream().map(DeviceBuilder::build).collect(Collectors.toList());
    }

    public DeviceDTO findDeviceById(UUID idToSearch){
        Device foundDevice = devicesRepo.findById(idToSearch).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToSearch);
        });

        return DeviceBuilder.build(foundDevice);
    }

    // Update
    public DeviceDTO updateDevice(UUID idToUpdate, DeviceDTO dto){
        Device updateDevice = devicesRepo.findById(idToUpdate).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToUpdate);
        });

        devicesRepo.save(DeviceBuilder.build(idToUpdate, dto));
        return dto;
    }

    // Delete
    public UUID deleteDevice(UUID idToDelete){
        Device deleteDevice = devicesRepo.findById(idToDelete).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToDelete);
        });

        devicesRepo.delete(deleteDevice);
        return idToDelete;
    }
}
