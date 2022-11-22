package ds.assign1.login.controller;

import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService SERVICE;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void login(@RequestBody CredentialsDTO credentialsDTO){
        SERVICE.login(credentialsDTO);
    }
}
