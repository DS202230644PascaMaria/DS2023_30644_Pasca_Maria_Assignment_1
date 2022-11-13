package ds.assign1.accounts.dtos.builders;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.accounts.entities.Account;
import ds.assign1.accounts.entities.Credentials;
import ds.assign1.accounts.entities.RoleType;

import java.util.List;
import java.util.UUID;

public class AccountBuilder {
    public static AccountDTO build(UUID id, String name, RoleType role, List<UUID> deviceList){
        return new AccountDTO(id, name, role, deviceList);
    }

    public static AccountDTO build(Account account){
        return new AccountDTO(account.getId(), account.getName(), account.getRole(), account.getDeviceList());
    }

    public static Account build(AccountDTO dto, Credentials credentials){
        return new Account(dto.getName(), dto.getRole(), credentials, dto.getDeviceList());
    }

    public static Account build(AccountDTO accountDTO, CredentialsDTO credentialsDTO){
        return new Account(accountDTO.getName(), accountDTO.getRole(), CredentialsBuilder.build(credentialsDTO), accountDTO.getDeviceList());
    }

    public static Account build(UUID id, AccountDTO accountDTO, Credentials credentials){
        return new Account(id, accountDTO.getName(), accountDTO.getRole(), credentials, accountDTO.getDeviceList());
    }
}
