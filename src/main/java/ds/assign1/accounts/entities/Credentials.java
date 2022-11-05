package ds.assign1.accounts.entities;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Credentials {
    String username;
    String password;
}
