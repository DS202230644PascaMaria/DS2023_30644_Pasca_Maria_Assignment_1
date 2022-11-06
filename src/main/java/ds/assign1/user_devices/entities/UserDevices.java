package ds.assign1.user_devices.entities;

import ds.assign1.accounts.entities.Account;
import ds.assign1.devices.entities.Device;
import ds.assign1.user_devices.dtos.UserDeviceBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_devices")
public class UserDevices {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    UUID id;

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    UUID accountId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<UUID> deviceIdList;

    public UserDevices(UUID accountId, List<UUID> deviceIdList){
        this.accountId = accountId;
        this.deviceIdList = deviceIdList;
    }
}
