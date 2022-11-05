package ds.assign1.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountDTO {
    AccountDTO accountDTO;
    CredentialsDTO credentialsDTO;
}
