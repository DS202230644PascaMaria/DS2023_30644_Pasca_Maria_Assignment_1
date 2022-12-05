package ds.assign1.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FullAccountDTO {
    UUID id;
    String name;
    String role;
    CredentialsDTO credentials;
}
