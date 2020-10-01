package routes;

import com.google.gson.Gson;
import domain.Order;
import spark.Request;
import spark.Response;
import spark.Route;
import storage.OrderStorage;

public class GetOrder implements Route {
    private final OrderStorage storage;
    private static final String QUERY_PARAM_ID = "id";

    public GetOrder(OrderStorage storage) {
        this.storage = storage;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.type("application/json");
        String id = request.queryParams(QUERY_PARAM_ID);
        Order order = storage.getOrder(id);

        if (order != null) {
            Gson gson = new Gson();
            response.status(200);
            return gson.toJson(order, Order.class);
        } else {
            response.status(400);
            return null;
        }
    }
}