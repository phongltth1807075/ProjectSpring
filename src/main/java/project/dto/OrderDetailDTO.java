package project.dto;

import project.model.OrderDetailEntity;

import java.util.List;

public class OrderDetailDTO {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private double productPrice;
    private double totalPrice;
    private int quantity;

    public OrderDetailDTO(List<OrderDetailEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            this.id = list.get(i).getId();
            this.orderId = list.get(i).getOrderId();
            this.productId = list.get(i).getProductId();
            this.productName = list.get(i).getProduct().getProductName();
            this.productPrice = list.get(i).getUnitPrice();
            this.quantity = list.get(i).getQuantity();
            this.totalPrice = list.get(i).getUnitPrice() * list.get(i).getQuantity();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
