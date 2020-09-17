package project.model;


import javax.persistence.*;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private String productName;
    private double totalProduct;


    @OneToOne()
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    public Warehouse(int id, int productId, String productName, double totalProduct, Product product) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.totalProduct = totalProduct;
        this.product = product;
    }

    public Warehouse() {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
