package project.dto;

import project.model.Warehouse;

public class WarehouseDTO {
    private int id;
    private int productId;
    private String productName;
    private double totalProduct;

    public WarehouseDTO(Warehouse warehouse) {
        this.id = warehouse.getId();
        this.productId = warehouse.getProductId();
        this.productName = warehouse.getProductName();
        this.totalProduct = warehouse.getTotalProduct();
    }

    public WarehouseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(double totalProduct) {
        this.totalProduct = totalProduct;
    }
}
