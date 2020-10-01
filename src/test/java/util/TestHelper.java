package util;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {
    public static final String ORDER_ID = "1";
    public static final String CUSTOMER_ID_1 = "1";
    public static final String CUSTOMER_ID_2 = "2";
    public static final String CUSTOMER_ID_3 = "3";

    public static Order getOrder(String customerId){
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(getOrderLinePaper());
        orderLines.add(getOrderLinePen());
        return new Order(customerId, orderLines);
    }

    public static Order getPadOrder(String customerId){
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(getOrderLinePad());
        return new Order(customerId, orderLines);
    }

    public static Order getLargeOrder(String customerId){
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(getLargeLine());
        return new Order(customerId, orderLines);
    }

    public static Customer getRegularCustomer(){
        return new Customer("Steve", CustomerType.REGULAR);
    }

    public static Customer getLargeCustomer(){
        return new Customer("Matt", CustomerType.LARGE);
    }

    public static Customer getPrivateCustomer(){
        return new Customer("Bob", CustomerType.PRIVATE);
    }

    private static OrderLine getOrderLinePen(){
        return new OrderLine(new Product("pen", 20.0, ProductType.Pen), 5);
    }

    private static OrderLine getOrderLinePad(){
        return new OrderLine(new Product("paper", 25.0, ProductType.Pad), 4);
    }

    private static OrderLine getOrderLinePaper(){
        return new OrderLine(new Product("pad", 25.0, ProductType.Paper), 4);
    }

    private static OrderLine getLargeLine(){
        return new OrderLine(new Product("Fancy paper with grey lines", 15000.0, ProductType.Pad), 1);
    }
}
