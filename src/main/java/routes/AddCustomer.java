package routes;

import com.google.gson.Gson;
import domain.Customer;
import domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import storage.CustomerStorage;
import storage.OrderStorageImpl;

public class AddCustomer implements Route {
    private final CustomerStorage storage;

    public AddCustomer(CustomerStorage storage) {
        this.storage = storage;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        if (request.body() != null) {
            Customer customer = gson.fromJson(request.body(), Customer.class);
            if (customer != null) {
                Customer customerWithId = setCustomerId(customer);
                storage.addCustomer(customerWithId);
                response.status(200);
                return gson.toJson(customerWithId, Customer.class);
            }
        }
        response.status(400);
        return "Failed to add customer";
    }

    private Customer setCustomerId(Customer customer) {
        return new Customer(customer.getName(), customer.getType());
    }
}
