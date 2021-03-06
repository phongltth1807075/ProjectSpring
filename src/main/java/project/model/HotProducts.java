package project.model;

import javax.persistence.*;

@Entity
public class HotProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotProductId;
    private int productId;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private HotProductStatus status;

    public enum HotProductStatus {
        Active, Deactive, Deleted
    }

    @OneToOne()
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;


    public HotProducts(int hotProductId, int productId, long createdAt, long updatedAt, long deletedAt, HotProductStatus status, Product product) {
        this.hotProductId = hotProductId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
        this.product = product;
    }

    public HotProducts() {
    }

    public int getHotProductId() {
        return hotProductId;
    }

    public void setHotProductId(int hotProductId) {
        this.hotProductId = hotProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public HotProductStatus getStatus() {
        return status;
    }

    public void setStatus(HotProductStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
