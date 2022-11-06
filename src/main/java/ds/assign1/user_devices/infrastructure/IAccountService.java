package ds.assign1.user_devices.infrastructure;

import ds.assign1.accounts.dtos.AccountDTO;

import java.util.UUID;

public interface IAccountService {
    AccountDTO findAccountById(UUID idToSearch);

}
