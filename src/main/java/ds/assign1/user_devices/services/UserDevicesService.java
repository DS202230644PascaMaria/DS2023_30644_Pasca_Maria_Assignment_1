package ds.assign1.user_devices.services;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.user_devices.dtos.OutUserDevicesDTO;
import ds.assign1.user_devices.dtos.UserDeviceDTO;
import ds.assign1.user_devices.dtos.UserDeviceBuilder;
import ds.assign1.user_devices.entities.UserDevices;
import ds.assign1.user_devices.infrastructure.IAccountService;
import ds.assign1.user_devices.infrastructure.IDeviceService;
import ds.assign1.user_devices.repo.UserDevicesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDevicesService {
    private final UserDevicesRepo userDevicesRepo;
    private final IAccountService ACCOUNT_SERVICE;
    private final IDeviceService deviceService;

    // Create
    public UUID createUserDeviceList(UserDeviceDTO userDeviceDTO){
        UserDevices userDevices = UserDeviceBuilder.build(userDeviceDTO);

        userDevices = userDevicesRepo.save(userDevices);
        return userDevices.getId();
    }

    // Read
    public List<OutUserDevicesDTO> showAll(){
        List<UserDevices> userDevices = userDevicesRepo.findAll();
        return userDevices.stream()
                .map(userDevice -> {
                    AccountDTO accountDTO = ACCOUNT_SERVICE.findAccountById(userDevice.getAccountId());
                    List<DeviceDTO> deviceList = userDevice.getDeviceIdList().stream()
                            .map(deviceService::findDeviceById)
                            .collect(Collectors.toList());
                    return new OutUserDevicesDTO(userDevice.getId(), accountDTO, deviceList);
                })
                .collect(Collectors.toList());
    }

    /**
     * find all devices associated with a user
     */
    public List<DeviceDTO> findDevicesOwnedById(UUID accountId){
        UserDevices foundUserDevices = userDevicesRepo.findByAccountId(accountId).orElseThrow(() ->{
            throw new ResourceNotFoundException(UserDevices.class.getSimpleName() + " with account id " + accountId);
        });

        return foundUserDevices.getDeviceIdList().stream()
                .map(deviceService::findDeviceById)
                .collect(Collectors.toList());
    }

    // Update
    public UUID insertDevice(UUID accountId, UUID deviceToAdd){
        UserDevices foundUserDevices = userDevicesRepo.findByAccountId(accountId).orElseThrow(() ->{
            throw new ResourceNotFoundException(UserDevices.class.getSimpleName() + " with account id " + accountId);
        });

        List<UUID> updatedDevicesList = foundUserDevices.getDeviceIdList();
        updatedDevicesList.add(deviceToAdd);

        userDevicesRepo.save(new UserDevices(foundUserDevices.getId(), foundUserDevices.getAccountId(), updatedDevicesList));
        return foundUserDevices.getId();
    }

    public UUID removeDevice(UUID accountId, UUID deviceToRemove){
        UserDevices foundUserDevices = userDevicesRepo.findByAccountId(accountId).orElseThrow(() ->{
            throw new ResourceNotFoundException(UserDevices.class.getSimpleName() + " with account id " + accountId);
        });

        List<UUID> updatedDevicesList = foundUserDevices.getDeviceIdList();

        if(!updatedDevicesList.contains(deviceToRemove)){
            throw new ResourceNotFoundException(UserDevices.class.getSimpleName() + " with device id " + deviceToRemove);
        }

        updatedDevicesList.remove(deviceToRemove);

        userDevicesRepo.save(new UserDevices(foundUserDevices.getId(), foundUserDevices.getAccountId(), updatedDevicesList));
        return foundUserDevices.getId();
    }

    // Delete
    public UUID deleteUserDevices(UUID accountId){
        UserDevices foundUserDevices = userDevicesRepo.findByAccountId(accountId).orElseThrow(() ->{
            throw new ResourceNotFoundException(UserDevices.class.getSimpleName() + " with account id " + accountId);
        });

        userDevicesRepo.delete(foundUserDevices);
        return foundUserDevices.getId();
    }
}
