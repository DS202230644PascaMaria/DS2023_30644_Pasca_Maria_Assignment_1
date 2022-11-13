package ds.assign1.accounts.services;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.accounts.dtos.builders.AccountBuilder;
import ds.assign1.accounts.dtos.builders.CredentialsBuilder;
import ds.assign1.accounts.entities.Account;
import ds.assign1.accounts.entities.Credentials;
import ds.assign1.accounts.repos.AccountRepo;
import ds.assign1.login.infrastructure.ILoginService;
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
public class AccountService implements ILoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepo accountRepo;

    public UUID createAccount(AccountDTO accountDTO, CredentialsDTO credentialsDTO){
        Account createdAccount = AccountBuilder.build(accountDTO, credentialsDTO);

        createdAccount = accountRepo.save(createdAccount);

        LOGGER.debug("Account with id {} was created", createdAccount.getId());
        return createdAccount.getId();
    }

    public List<AccountDTO> findAccounts(){
        List<Account> accountList = accountRepo.findAll();
        return accountList.stream().map(AccountBuilder::build).collect(Collectors.toList());
    }

    public AccountDTO findAccountById(UUID idToSearch){
        Optional<Account> foundAccount = accountRepo.findById(idToSearch);
        if(!foundAccount.isPresent()){
            LOGGER.error("There's no such account with id {}", idToSearch);
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToSearch);
        }

        return AccountBuilder.build(foundAccount.get());
    }

    public UUID updateAccount(UUID idToUpdate, AccountDTO dto){
        Account account = accountRepo.findById(idToUpdate).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToUpdate);
        });

        AccountDTO updatedAccount = AccountBuilder.build(account);
        System.out.println(updatedAccount.getId() + " " +
                            updatedAccount.getName());


        if(!dto.getName().isEmpty()){
            updatedAccount.setName(dto.getName());
        }

        updatedAccount.setRole(dto.getRole());

        accountRepo.save(AccountBuilder.build(updatedAccount.getId(), updatedAccount, account.getCredentials()));

        return updatedAccount.getId();
    }

    public UUID updateCredentials(UUID idToUpdate, CredentialsDTO dto){
        Account account = accountRepo.findById(idToUpdate).orElseThrow(() -> {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id " + idToUpdate);
        });

        AccountDTO updatedAccount = AccountBuilder.build(account);
        System.out.println(updatedAccount.getId());
        Credentials updatedCredentials = CredentialsBuilder.build(dto);

        Account toSave = AccountBuilder.build(updatedAccount.getId(), updatedAccount, updatedCredentials);
        System.out.println(toSave.getId());
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
    public boolean checkPassword(UUID idToCheck, String password) {
        return accountRepo.findById(idToCheck)
                .get().getCredentials()
                .getPassword().equals(password);
    }
}
