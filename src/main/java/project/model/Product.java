package project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
    private ProductStatus status;
    @Column(name = "account_id")
    private int accountId;

    @ManyToOne() //EAGER
    @JoinColumn(name = "CategoryId", insertable = false, updatable = false)
    private Category category;

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Image> images = new HashSet<>();

    @OneToOne()
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Accounts accounts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<OrderDetailEntity> orderDetailEntitySet = new HashSet<>();

    public enum ProductStatus {
        Active, Deactive, Deleted
    }

    public Product() {
    }

    public Product(int productId, int categoryId, String productName, double productPrice, String description, long createdAt, long updatedAt, long deletedAt, String imageProduct, ProductStatus status, int accountId, Category category, Set<Image> images, Accounts accounts, Set<OrderDetailEntity> orderDetailEntitySet) {
        this.productId = productId;
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
        this.images = images;
        this.accounts = accounts;
        this.orderDetailEntitySet = orderDetailEntitySet;
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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Set<OrderDetailEntity> getOrderDetailEntitySet() {
        return orderDetailEntitySet;
    }

    public void setOrderDetailEntitySet(Set<OrderDetailEntity> orderDetailEntitySet) {
        this.orderDetailEntitySet = orderDetailEntitySet;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
