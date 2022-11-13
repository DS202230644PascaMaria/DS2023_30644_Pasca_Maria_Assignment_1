package ds.assign1.mapping;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.mapping.infrastructure.IAccountService;
import ds.assign1.mapping.infrastructure.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MappingService {
    private final IAccountService ACCOUNT_SERVICE;
    private final IDeviceService DEVICE_SERVICE;

    public List<DeviceDTO> getOwnedDevices(UUID accountId){
        return ACCOUNT_SERVICE.getOwnedDevices(accountId)
                .stream()
                .map(DEVICE_SERVICE::findDeviceById)
                .collect(Collectors.toList());
    }

    public Map<UUID, List<DeviceDTO>> getMappings(){
        List<AccountDTO> accountDTOS = ACCOUNT_SERVICE.findAccounts();

        Map<UUID, List<DeviceDTO>> output = new HashMap<>();

        for(AccountDTO dto: accountDTOS){
            output.put(dto.getId(), dto.getDeviceList().stream()
                                        .map(DEVICE_SERVICE::findDeviceById)
                                        .collect(Collectors.toList()));
        }

        return output;
    }

    public void insertDevice(UUID accountId, UUID deviceId){
        // validators

        ACCOUNT_SERVICE.insertDevice(accountId, deviceId);
        DEVICE_SERVICE.addOwner(accountId, deviceId);
    }

    public void removeDevice(UUID accountId, UUID deviceId){
        // validators

        ACCOUNT_SERVICE.removeDevice(accountId, deviceId);
        DEVICE_SERVICE.removeOwner(deviceId);
    }
}
