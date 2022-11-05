package ds.assign1.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private Credentials credentials;

    public Account(String name, RoleType role, Credentials credentials) {
        this.name = name;
        this.role = role;
        this.credentials = credentials;
    }

    public void setId(UUID id){
        this.id = id;
    }
}
