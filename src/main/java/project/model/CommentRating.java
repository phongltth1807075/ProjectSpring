package project.model;


import javax.persistence.*;

@Entity
public class CommentRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int accountId;
    private int value;
    private String Comment;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private CommentRatingStatus status;


    @OneToOne()
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Accounts accounts;

    @OneToOne()
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    public enum CommentRatingStatus {
        Active, Deactive, Deleted
    }


    public CommentRating(int id, int productId, int accountId, int value, String comment, long createdAt, long updatedAt, long deletedAt, CommentRatingStatus status, Accounts accounts, Product product) {
        this.id = id;
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
        Comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
        this.accounts = accounts;
        this.product = product;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CommentRating() {
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

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
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

    public CommentRatingStatus getStatus() {
        return status;
    }

    public void setStatus(CommentRatingStatus status) {
        this.status = status;
    }
}
