package project.model;


import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int accountId;
    private int value;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private RatingStatus status;

    @OneToOne()
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @OneToOne()
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Accounts account;


    public enum RatingStatus {
        Active, Deactive, Deleted
    }

    public Rating(int id, int productId, int accountId, int value, long createdAt, long updatedAt, long deletedAt, RatingStatus status, Product product, Accounts account) {
        this.id = id;
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
        this.product = product;
        this.account = account;
    }


    public Rating() {
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public RatingStatus getStatus() {
        return status;
    }

    public void setStatus(RatingStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }
}
