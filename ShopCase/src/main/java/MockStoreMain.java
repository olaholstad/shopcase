import routes.*;
import spark.Spark;
import storage.CustomerStorage;
import storage.CustomerStorageImpl;
import storage.OrderStorageImpl;
import util.Configuration;
import util.ConfigurationImpl;

public class MockStoreMain {

    public static void main(String[] args) {
        Configuration configuration = ConfigurationImpl.getInstance();
        new MockStoreMain().run(configuration);
    }

    public void run(Configuration configuration){
        CustomerStorage customerStorage = CustomerStorageImpl.getInstance();
        OrderStorageImpl orderStorage = OrderStorageImpl.getInstance();
        Spark.port(configuration.getPort());

        Spark.get("/customer", new GetCustomer(customerStorage));
        Spark.post("/customer", new AddCustomer(customerStorage));

        Spark.get("/order", new GetOrder(orderStorage));
        Spark.post("/order", new PlaceOrder(orderStorage, customerStorage));
        Spark.put("/order", new UpdateOrder(orderStorage, customerStorage));
    }
}
