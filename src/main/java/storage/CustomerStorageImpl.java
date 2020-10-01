package storage;

import domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CustomerStorageImpl implements CustomerStorage {

    private static final Logger logger = LoggerFactory.getLogger(CustomerStorageImpl.class);
    private final Map<String, Customer> customerMap = new HashMap<>();
    private static final CustomerStorageImpl instance = new CustomerStorageImpl();

    private CustomerStorageImpl(){}

    public static CustomerStorageImpl getInstance(){
        return instance;
    }

    @Override
    public void addCustomer(Customer customer) {
        if (customer != null && customer.getId() != null){
            customerMap.put(customer.getId(), customer);
            logger.debug("Added customer {}", customer.toString());
        }
        logger.debug("Something went wrong while adding customer");
    }

    @Override
    public Customer getCustomer(String id) {
        if (id != null){
            return customerMap.get(id);
        }
        logger.debug("Something went wrong while fetching customer");
        return null;
    }
}
