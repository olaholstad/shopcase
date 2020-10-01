package util;

import domain.*;
import storage.CustomerStorage;

import java.util.ArrayList;

public class OrderCalculator {
    private final CustomerStorage customerStorage;

    public OrderCalculator(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    public Order setOrderSumAndDiscount(Order order) {
        double totalSum = 0.0;
        double totalDiscount = 0.0;

        ArrayList<OrderLine> updatedLines = new ArrayList<>();
        Customer customer = customerStorage.getCustomer(order.getCustomerId());
        for (OrderLine line : order.getLines()) {
            //Setting sum and discount for large customers
            if (isExtraDiscountApplicable(customer.getType(), line.getProduct().getType())) {
                double sum = line.getProduct().getPrice() * line.getAmount();
                double lineSum = sum * getDiscount(customer) * 0.8;
                double lineDiscount = sum - lineSum;

                totalSum = totalSum + lineSum;
                totalDiscount = totalDiscount + lineDiscount;
                updatedLines.add(getUpdatedLine(line, lineSum, lineDiscount));
            //Setting sum and discount for regular and private customers
            } else {
                double sum = line.getProduct().getPrice() * line.getAmount();
                double lineSum = sum * getDiscount(customer);
                double lineDiscount = sum - lineSum;

                totalSum = totalSum + lineSum;
                totalDiscount = totalDiscount + lineDiscount;
                updatedLines.add(getUpdatedLine(line, lineSum, lineDiscount));
            }
        }
        return new Order(order.getCustomerId(), order.getOrderId(), updatedLines, totalSum, totalDiscount, hasBicycleReward(totalSum));
    }

    private double getDiscount(Customer customer) {
        switch (customer.getType()) {
            case REGULAR:
            case LARGE:
                return 0.9;
            default:
                return 1.0;
        }
    }

    private boolean hasBicycleReward(double totalSum){
        return totalSum > 10000.0;
    }

    private boolean isExtraDiscountApplicable(CustomerType customerType, ProductType productType) {
        return CustomerType.LARGE.equals(customerType) && ProductType.Pen.equals(productType) || CustomerType.LARGE.equals(customerType) && ProductType.Paper.equals(productType);
    }

    private OrderLine getUpdatedLine(OrderLine line, double lineSum, double lineDiscount) {
        return new OrderLine(line.getProduct(), line.getAmount(), lineSum, lineDiscount);
    }
}
