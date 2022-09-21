package Enums;

public enum Accounts {
    VALID_USERNAME("0352602024"),
    VALID_PASSWORD("t123456789");

    private final String value;

    Accounts(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
