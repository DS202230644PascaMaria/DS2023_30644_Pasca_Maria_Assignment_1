package ds.assign1.accounts.controllers;

import ds.assign1.accounts.dtos.*;
import ds.assign1.accounts.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// 5596b281-97d2-4693-a41b-01076ee1f0f3 - johndoe
// 11b860df-03ae-4a2c-86ab-2e0f731c75e4 - testAdmin

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ReturnAccountDTO> getAllAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ReturnAccountDTO getAccountById(@PathVariable("id") UUID idToSearch){
        return accountService.findAccountById(idToSearch);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountIdDTO createAccount(@RequestBody CreateAccountDTO dto){
        return new AccountIdDTO(accountService.createAccount(dto.getAccountDTO(), dto.getCredentialsDTO()));
    }

    @PutMapping("/update_account/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountIdDTO updateAccount(@PathVariable("id") UUID idToUpdate, @RequestBody AccountDTO accountDTO){
        return new AccountIdDTO(accountService.updateAccount(idToUpdate, accountDTO));
    }

    @PutMapping("/update_credentials/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountIdDTO updateCredentials(@PathVariable("id") UUID idToUpdate, @RequestBody CredentialsDTO credentialsDTO){
        return new AccountIdDTO(accountService.updateCredentials(idToUpdate, credentialsDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public AccountIdDTO deleteAccount(@PathVariable("id") UUID idToDelete){
        return new AccountIdDTO(accountService.deleteAccount(idToDelete));
    }
}
