package ds.assign1.accounts.dtos.builders;

import ds.assign1.accounts.dtos.CredentialsDTO;
import ds.assign1.accounts.entities.Credentials;

public class CredentialsBuilder {
    public static Credentials build(CredentialsDTO dto){
        return new Credentials(dto.getUsername(), dto.getPassword());
    }

    public static CredentialsDTO build(Credentials credentials){
        return new CredentialsDTO(credentials.getUsername(), credentials.getPassword());
    }

    public static Credentials build(String username, String password){
        return new Credentials(username, password);
    }
}
