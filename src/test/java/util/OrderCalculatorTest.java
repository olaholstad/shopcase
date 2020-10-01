package util;

import com.google.gson.Gson;
import domain.Customer;
import domain.Order;
import org.junit.Before;
import org.junit.Test;
import storage.CustomerStorage;
import storage.CustomerStorageImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCalculatorTest {
    private OrderCalculator orderCalculator;
    private CustomerStorage customerStorage;

    @Before
    public void setUp(){
        customerStorage = CustomerStorageImpl.getInstance();
        orderCalculator = new OrderCalculator(customerStorage);
    }

    @Test
    public void shouldSetRegularCompanyDiscount(){
        Customer customer = TestHelper.getRegularCustomer();
        customerStorage.addCustomer(customer);
        Order order = TestHelper.getOrder(customer.getId());

        assertThat(order.getSum()).isEqualTo(0.0);
        assertThat(order.getDiscount()).isEqualTo(0.0);

        Order updatedOrder = orderCalculator.setOrderSumAndDiscount(order);

        assertThat(updatedOrder.getSum()).isEqualTo(180.0);
        assertThat(updatedOrder.getDiscount()).isEqualTo(20.0);
    }

    @Test
    public void shouldSetLargeCompanyPenAndPaperDiscount(){
        Customer customer = TestHelper.getLargeCustomer();
        customerStorage.addCustomer(customer);

        Order padOrder = TestHelper.getPadOrder(customer.getId());

        assertThat(padOrder.getSum()).isEqualTo(0.0);
        assertThat(padOrder.getDiscount()).isEqualTo(0.0);

        Order updatedPadOrder = orderCalculator.setOrderSumAndDiscount(padOrder);

        assertThat(updatedPadOrder.getSum()).isEqualTo(90.0);
        assertThat(updatedPadOrder.getDiscount()).isEqualTo(10.0);

        Order order = TestHelper.getOrder(customer.getId());

        assertThat(order.getSum()).isEqualTo(0.0);
        assertThat(order.getDiscount()).isEqualTo(0.0);

        Order updatedOrder = orderCalculator.setOrderSumAndDiscount(order);

        assertThat(updatedOrder.getSum()).isEqualTo(144.0);
        assertThat(updatedOrder.getDiscount()).isEqualTo(56.0);
    }

    @Test
    public void shouldNotGiveDiscountForPrivateCustomer(){
        Customer customer = TestHelper.getPrivateCustomer();
        customerStorage.addCustomer(customer);

        Order padOrder = TestHelper.getPadOrder(customer.getId());

        assertThat(padOrder.getSum()).isEqualTo(0.0);
        assertThat(padOrder.getDiscount()).isEqualTo(0.0);

        Order updatedPadOrder = orderCalculator.setOrderSumAndDiscount(padOrder);

        assertThat(updatedPadOrder.getSum()).isEqualTo(100.0);
        assertThat(updatedPadOrder.getDiscount()).isEqualTo(0.0);
    }

    @Test
    public void shouldTriggerBicycleReward(){
        Customer customer = TestHelper.getRegularCustomer();
        customerStorage.addCustomer(customer);
        Order order = TestHelper.getOrder(customer.getId());
        Order updatedOrder = orderCalculator.setOrderSumAndDiscount(order);

        assertThat(updatedOrder.hasBicycleReward()).isFalse();


        Order largeOrder = TestHelper.getLargeOrder(customer.getId());
        Order updatedLargeOrder = orderCalculator.setOrderSumAndDiscount(largeOrder);

        assertThat(updatedLargeOrder.hasBicycleReward()).isTrue();
    }

    @Test
    public void generateTestOrder(){
        Order order = TestHelper.getOrder("132");
        Gson gson = new Gson();
        System.out.println(gson.toJson(order, Order.class));
    }
}