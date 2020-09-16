package project.model;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private imageStatus status;
    private int productId;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;

    public enum imageStatus {
        Active, Deactive, Deleted
    }

    @ManyToOne() //EAGER
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product products;

    public Image(int id, String url, imageStatus status, int productId, long createdAt, long updatedAt, long deletedAt, Product products) {
        this.id = id;
        this.url = url;
        this.status = status;
        this.productId = productId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.products = products;
    }

    public Image() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public imageStatus getStatus() {
        return status;
    }

    public void setStatus(imageStatus status) {
        this.status = status;
    }


    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
