package ds.assign1.login.infrastructure;

import java.util.UUID;

public interface ILoginService {
    UUID findAccountByUsername(String username);

    boolean checkPassword(UUID idToCheck, String password);
}
