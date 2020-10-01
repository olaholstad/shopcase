package routes;

import com.google.gson.Gson;
import domain.Order;
import spark.Request;
import spark.Response;
import spark.Route;
import storage.CustomerStorage;
import storage.OrderStorage;
import util.OrderCalculator;

public class UpdateOrder implements Route {
    private final OrderStorage orderStorage;
    private final OrderCalculator calculator;

    public UpdateOrder(OrderStorage orderStorage, CustomerStorage customerStorage) {
        this.orderStorage = orderStorage;
        this.calculator = new OrderCalculator(customerStorage);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        if (request.body() != null) {
            Order order = gson.fromJson(request.body(), Order.class);
            if (order != null) {
                Order updatedOrder = calculator.setOrderSumAndDiscount(order);
                orderStorage.updateOrder(updatedOrder);
                response.status(200);
                return gson.toJson(updatedOrder, Order.class);
            }
        }
        response.status(400);
        return "Failed to update order";
    }
}