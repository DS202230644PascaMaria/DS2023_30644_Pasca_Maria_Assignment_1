package ds.assign1.mapping.infrastructure;

import ds.assign1.accounts.dtos.AccountDTO;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
    List<UUID> getOwnedDevices(UUID accountId);
    List<AccountDTO> findAccounts();
    UUID removeDevice(UUID accountId, UUID deviceId);
    void insertDevice(UUID accountId, UUID deviceId);
}
