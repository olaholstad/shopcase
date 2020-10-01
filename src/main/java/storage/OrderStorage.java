package storage;

import domain.Order;

public interface OrderStorage {
    void addOrder(Order order);
    Order getOrder(String id);
    void updateOrder(Order order);
}