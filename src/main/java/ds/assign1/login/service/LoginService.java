package ds.assign1.login.service;

import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.login.infrastructure.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final ILoginService SERVICE;

    public void login(CredentialsDTO credentialsDTO){
        UUID accountId = SERVICE.findAccountByUsername(credentialsDTO.getUsername());
        if(!SERVICE.checkPassword(accountId, credentialsDTO.getPassword())){
            throw new RuntimeException("Wrong username or password");
        }
    }
}
