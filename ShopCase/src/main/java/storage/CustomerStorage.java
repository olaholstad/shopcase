package storage;

import domain.Customer;

public interface CustomerStorage {
    void addCustomer(Customer customer);
    Customer getCustomer(String id);
}
