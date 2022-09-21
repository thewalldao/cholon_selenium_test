package Models;

import Enums.Accounts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account() {
        this.username = Accounts.VALID_USERNAME.getValue();
        this.password = Accounts.VALID_PASSWORD.getValue();
    }
}
