package project.model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private int categoryId;
    private String productName;
    private double productPrice;
    private String description;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private String imageProduct;
    private int status;

    @Column(name = "account_id")
    private int accountId;

    @ManyToOne() //EAGER
    @JoinColumn(name = "CategoryId", insertable = false, updatable = false)
    private Category category;


    @OneToOne()
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Accounts accounts;


    public Product() {
    }

    public Product(int categoryId, String productName, double productPrice, String description, long createdAt, long updatedAt, long deletedAt, String imageProduct, int status, int accountId, Category category, Accounts accounts) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.imageProduct = imageProduct;
        this.status = status;
        this.accountId = accountId;
        this.category = category;
        this.accounts = accounts;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }
}
