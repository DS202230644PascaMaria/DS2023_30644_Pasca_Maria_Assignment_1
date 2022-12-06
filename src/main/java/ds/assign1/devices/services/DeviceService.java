package ds.assign1.devices.services;

import ds.assign1.accounts.entities.Account;
import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.devices.dtos.builders.DeviceBuilder;
import ds.assign1.devices.entities.Device;
import ds.assign1.devices.repos.DevicesRepo;
import ds.assign1.mapping.infrastructure.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService implements IDeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    private final DevicesRepo devicesRepo;
    private final DeviceValidators VALIDATOR;

    // Create
    public UUID addDevice(DeviceDTO dto){
        VALIDATOR.hourlyConsumptionValidator(dto.getMaxHourlyConsumption());
        VALIDATOR.descriptionValidator(dto.getDescription());
        //VALIDATOR.addressValidator(dto.getAddress());

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

        DeviceDTO updatedDevice = DeviceBuilder.build(updateDevice);

        if(dto.getAddress().length() != 0){
            //VALIDATOR.addressValidator(dto.getAddress());
            updatedDevice.setAddress(dto.getAddress());
        }

        if(dto.getDescription().length() != 0){
            VALIDATOR.descriptionValidator(dto.getDescription());
            updatedDevice.setDescription(dto.getDescription());
        }

        if(dto.getMaxHourlyConsumption() > 0){
            VALIDATOR.hourlyConsumptionValidator(dto.getMaxHourlyConsumption());
            updatedDevice.setMaxHourlyConsumption(dto.getMaxHourlyConsumption());
        }

        devicesRepo.save(DeviceBuilder.build(idToUpdate, updatedDevice));
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

    public void addOwner(UUID accountId, UUID deviceId){
        Device device = devicesRepo.findById(deviceId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + deviceId);
        });

        DeviceDTO dto = DeviceBuilder.build(device);

        dto.setPairedAccountId(accountId);
        devicesRepo.save(DeviceBuilder.build(deviceId, dto));
    }

    public void removeOwner(UUID deviceId){
        Device device = devicesRepo.findById(deviceId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + deviceId);
        });

        DeviceDTO dto = DeviceBuilder.build(device);

        dto.setPairedAccountId(null);
        devicesRepo.save(DeviceBuilder.build(deviceId, dto));
    }

    public UUID getOwner(UUID deviceId){
        return devicesRepo.findById(deviceId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + deviceId);
        }).getPairedAccountId();
    }
}
