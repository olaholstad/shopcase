package domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private String orderId;
    private String customerId;
    private List<OrderLine> lines;
    private double sum;
    private double discount;
    private boolean bicycleReward;

    public Order(String customerId, List<OrderLine> lines) {
        this.customerId = customerId;
        this.lines = lines;
        this.sum = 0.0;
        this.discount = 0.0;
        this.orderId = UUID.randomUUID().toString();
        this.bicycleReward = false;
    }

    public Order(String customerId, String orderId, List<OrderLine> lines, double sum, double discount) {
        this.customerId = customerId;
        this.lines = lines;
        this.sum = sum;
        this.discount = discount;
        this.orderId = orderId;
        this.bicycleReward = false;
    }

    public Order(String customerId, String orderId, List<OrderLine> lines, double sum, double discount, boolean hasBicycleReward) {
        this.customerId = customerId;
        this.lines = lines;
        this.sum = sum;
        this.discount = discount;
        this.orderId = orderId;
        this.bicycleReward = hasBicycleReward;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public double getSum() {
        return sum;
    }

    public double getDiscount() {
        return discount;
    }

    public String getOrderId() {
        return orderId;
    }

    public boolean hasBicycleReward() {
        return bicycleReward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Double.compare(order.sum, sum) == 0 &&
                Double.compare(order.discount, discount) == 0 &&
                bicycleReward == order.bicycleReward &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(lines, order.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, lines, sum, discount, bicycleReward);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", lines=" + lines +
                ", sum=" + sum +
                ", discount=" + discount +
                ", hasBicycleReward=" + bicycleReward +
                '}';
    }
}