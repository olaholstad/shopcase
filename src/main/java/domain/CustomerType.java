package domain;

public enum CustomerType {

    REGULAR("Regular"),
    LARGE("Large"),
    PRIVATE("Private");

    private final String value;

    CustomerType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
