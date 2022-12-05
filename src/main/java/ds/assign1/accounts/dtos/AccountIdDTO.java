package ds.assign1.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountIdDTO {
    private UUID id;
}
