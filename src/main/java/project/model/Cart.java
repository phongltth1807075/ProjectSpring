package project.model;

public class Cart {
    private int productId;
    private String productName;
    private int quantity;
    private double productPrice;
    private String property;
    private int sellerId;

    public Cart(int productId, String productName, int quantity, double productPrice, String property, int sellerId) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.property = property;
        this.sellerId = sellerId;
    }

    public Cart() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}


