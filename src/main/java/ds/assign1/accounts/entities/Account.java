package ds.assign1.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "paired_devices", joinColumns = @JoinColumn(name = "account_id"))
    private List<UUID> deviceList;

    public Account(String name, RoleType role, Credentials credentials, List<UUID> deviceList) {
        this.name = name;
        this.role = role;
        this.credentials = credentials;
        this.deviceList = deviceList;
    }

    public void setId(UUID id){
        this.id = id;
    }
}
