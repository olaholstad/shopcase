package storage;

import domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OrderStorageImpl implements OrderStorage {
    private static final Logger logger = LoggerFactory.getLogger(OrderStorageImpl.class);

    private final Map<String, Order> orderMap = new HashMap<>();
    private static final OrderStorageImpl instance = new OrderStorageImpl();

    private OrderStorageImpl() {
    }

    public static OrderStorageImpl getInstance() {
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        if (order != null && order.getOrderId() != null) {
            orderMap.put(order.getOrderId(), order);
            logger.debug("Added order {}", order.toString());
        }
        logger.debug("Something went wrong while adding order");

    }

    @Override
    public Order getOrder(String id) {
        if (id != null) {
            return orderMap.get(id);
        }
        logger.debug("Something went wrong while getting order");
        return null;
    }

    @Override
    public void updateOrder(Order order) {
        if (order != null && order.getOrderId() != null) {
            orderMap.remove(order.getOrderId());
            orderMap.put(order.getOrderId(), order);
            logger.debug("Updated order {}", order.toString());
        }
        logger.debug("Something went wrong while updating order");

    }
}
