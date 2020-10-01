package routes;

import com.google.gson.Gson;
import domain.Customer;
import spark.Request;
import spark.Response;
import spark.Route;
import storage.CustomerStorage;

public class GetCustomer implements Route {
    private final CustomerStorage storage;
    private static final String QUERY_PARAM_ID = "id";

    public GetCustomer(CustomerStorage storage) {
        this.storage = storage;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        String id = request.queryParams(QUERY_PARAM_ID);
        Customer customer = storage.getCustomer(id);
        if (customer != null) {
            Gson gson = new Gson();
            response.status(200);
            return gson.toJson(customer, Customer.class);
        } else {
            response.status(400);
            return null;
        }
    }

}