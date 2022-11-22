package ds.assign1.accounts.dtos;

import ds.assign1.accounts.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountDTO {
    UUID id;
    String name;
    String role;
    List<UUID> deviceList;
}
