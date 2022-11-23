package ds.assign1.login.infrastructure;

import java.util.UUID;

public interface ILoginService {
    UUID findAccountByUsername(String username);
    String getRole(UUID id);
    boolean checkPassword(UUID idToCheck, String password);
}
