package ds.assign1.accounts.controllers;

import ds.assign1.accounts.dtos.AccountDTO;
import ds.assign1.accounts.dtos.CreateAccountDTO;
import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.accounts.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts(){
        return accountService.findAccounts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountDTO getAccountById(@PathVariable("id") UUID idToSearch){
        return accountService.findAccountById(idToSearch);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createAccount(@RequestBody CreateAccountDTO dto){
        return accountService.createAccount(dto.getAccountDTO(), dto.getCredentialsDTO());
    }

    @PutMapping("/update_account/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID updateAccount(@PathVariable("id") UUID idToUpdate, @RequestBody AccountDTO accountDTO){
        return accountService.updateAccount(idToUpdate, accountDTO);
    }

    @PutMapping("/update_credentials/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID updateCredentials(@PathVariable("id") UUID idToUpdate, @RequestBody CredentialsDTO credentialsDTO){
        return accountService.updateCredentials(idToUpdate, credentialsDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public UUID deleteAccount(@PathVariable("id") UUID idToDelete){
        return accountService.deleteAccount(idToDelete);
    }
}
