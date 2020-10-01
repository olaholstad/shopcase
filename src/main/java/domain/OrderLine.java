package domain;

public class OrderLine {
    private Product product;
    private int amount;
    private double lineSum;
    private double lineDiscount;

    public OrderLine(Product product, int amount) {
        this.product = product;
        this.amount = amount;
        this.lineSum = 0.0;
        this.lineDiscount = 0.0;
    }

    public OrderLine(Product product, int amount, double lineSum, double lineDiscount) {
        this.product = product;
        this.amount = amount;
        this.lineSum = lineSum;
        this.lineDiscount = lineDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public double getLineSum() {
        return lineSum;
    }

    public double getLineDiscount() {
        return lineDiscount;
    }
}