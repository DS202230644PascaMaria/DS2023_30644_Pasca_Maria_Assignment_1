package ds.assign1.accounts.services;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.accounts.dtos.FullAccountDTO;
import ds.assign1.accounts.dtos.ReturnAccountDTO;
import ds.assign1.accounts.dtos.builders.AccountBuilder;
import ds.assign1.accounts.dtos.builders.CredentialsBuilder;
import ds.assign1.accounts.dtos.builders.FullAccountBuilder;
import ds.assign1.accounts.entities.Account;
import ds.assign1.accounts.entities.Credentials;
import ds.assign1.accounts.repos.AccountRepo;
import ds.assign1.login.infrastructure.ILoginService;
import ds.assign1.mapping.infrastructure.IAccountService;
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
public class AccountService implements ILoginService, IAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepo accountRepo;

    private final AccountValidators VALIDATORS;

    public FullAccountDTO createAccount(AccountDTO accountDTO, CredentialsDTO credentialsDTO){
        VALIDATORS.usernameValidators(credentialsDTO.getUsername());
        VALIDATORS.passwordValidator(credentialsDTO.getPassword());

        VALIDATORS.nameValidator(accountDTO.getName());

        Account createdAccount = AccountBuilder.build(accountDTO, credentialsDTO);

        createdAccount = accountRepo.save(createdAccount);

        return FullAccountBuilder.build(createdAccount);
    }

    public List<ReturnAccountDTO> getAccounts(){
        List<Account> accountList = accountRepo.findAll();
        return accountList.stream().map(AccountBuilder::buildReturn).collect(Collectors.toList());
    }

    public List<AccountDTO> findAccounts(){
        List<Account> accountList = accountRepo.findAll();
        return accountList.stream().map(AccountBuilder::build).collect(Collectors.toList());
    }

    public ReturnAccountDTO findAccountById(UUID idToSearch){
        Optional<Account> foundAccount = accountRepo.findById(idToSearch);
        if(!foundAccount.isPresent()){
            LOGGER.error("There's no such account with id {}", idToSearch);
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToSearch);
        }

        return AccountBuilder.buildReturn(foundAccount.get());
    }

    public UUID updateAccount(UUID idToUpdate, AccountDTO dto){
        Account account = accountRepo.findById(idToUpdate).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToUpdate);
        });

        AccountDTO updatedAccount = AccountBuilder.build(account);
        System.out.println(updatedAccount.getId() + " " +
                            updatedAccount.getName());

        VALIDATORS.nameValidator(dto.getName());

        if(dto.getName().length() != 0){
            updatedAccount.setName(dto.getName());
        }

        if(dto.getRole() != null){
            updatedAccount.setRole(dto.getRole());
        }

        accountRepo.save(AccountBuilder.build(updatedAccount.getId(), updatedAccount, account.getCredentials()));

        return updatedAccount.getId();
    }

    public UUID updateCredentials(UUID idToUpdate, CredentialsDTO dto){
        VALIDATORS.usernameValidators(dto.getUsername());
        VALIDATORS.passwordValidator(dto.getPassword());

        Account account = accountRepo.findById(idToUpdate).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToUpdate);
        });

        AccountDTO updatedAccount = AccountBuilder.build(account);
        Credentials updatedCredentials = CredentialsBuilder.build(dto);

        Account toSave = AccountBuilder.build(updatedAccount.getId(), updatedAccount, updatedCredentials);
        accountRepo.save(toSave);

        return updatedAccount.getId();
    }

    public UUID deleteAccount(UUID idToDelete) {
        Account account = accountRepo.findById(idToDelete).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToDelete);
        });

        accountRepo.delete(account);

        return account.getId();
    }

    @Override
    public UUID findAccountByUsername(String username) {
        List<Account> accountList = accountRepo.findAll();
        for(Account account : accountList){
            if(account.getCredentials().getUsername().equals(username)){
                return account.getId();
            }
        }

        throw new RuntimeException("Wrong username");
    }

    @Override
    public String getRole(UUID id) {
        return findAccountById(id).toString();
    }

    @Override
    public boolean checkPassword(UUID idToCheck, String password) {
        return accountRepo.findById(idToCheck)
                .get().getCredentials()
                .getPassword().equals(password);
    }


    public List<UUID> getOwnedDevices(UUID accountId){
        return accountRepo.findById(accountId).orElseThrow(() ->{
            throw new RuntimeException("This account does not exist");
        }).getDeviceList();
    }

    public void insertDevice(UUID accountId, UUID deviceId){
        Account account = accountRepo.findById(accountId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + accountId);
        });

        AccountDTO dto = AccountBuilder.build(account);
        List<UUID> deviceList = dto.getDeviceList();
        deviceList.add(deviceId);
        dto.setDeviceList(deviceList);

        accountRepo.save(AccountBuilder.build(accountId, dto, account.getCredentials()));
    }

    public UUID removeDevice(UUID accountId, UUID deviceId){
        Account account = accountRepo.findById(accountId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + accountId);
        });

        AccountDTO dto = AccountBuilder.build(account);
        List<UUID> deviceList = dto.getDeviceList();
        deviceList.remove(deviceId);
        dto.setDeviceList(deviceList);

        accountRepo.save(AccountBuilder.build(accountId, dto, account.getCredentials()));
        return deviceId;
    }
}
