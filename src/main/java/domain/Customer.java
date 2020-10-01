package domain;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private String id;
    private String name;
    private CustomerType type;

    public Customer(String name, CustomerType type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                type == customer.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
