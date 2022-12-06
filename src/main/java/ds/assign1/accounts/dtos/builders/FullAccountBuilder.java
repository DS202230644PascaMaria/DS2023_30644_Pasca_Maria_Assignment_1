package ds.assign1.accounts.dtos.builders;

import ds.assign1.accounts.dtos.FullAccountDTO;
import ds.assign1.accounts.entities.Account;

public class FullAccountBuilder {
    public static FullAccountDTO build(Account entity){
        return new FullAccountDTO(entity.getId(),
                        entity.getName(),
                        entity.getRole().toString(),
                        CredentialsBuilder.build(entity.getCredentials()));
    }
}
