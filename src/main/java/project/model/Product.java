package project.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProductId;
    private int CategoryId;
    private String ProductName;
    private double ProductPrice;
    private String Description;
    private long CreatedAt;
    private long UpdatedAt;
    private long DeletedAt;
    private String ImageProduct;
    private int Status;

    @ManyToOne() //EAGER
    @JoinColumn(name = "CategoryId", insertable = false, updatable = false)
    private Category category;


    public Product() {
    }

    public Product(int categoryId, String productName, double productPrice, String description, long createdAt, long updatedAt, long deletedAt, String imageProduct, int status, Category category) {
        CategoryId = categoryId;
        ProductName = productName;
        ProductPrice = productPrice;
        Description = description;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        ImageProduct = imageProduct;
        Status = status;
        this.category = category;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public long getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(long createdAt) {
        CreatedAt = createdAt;
    }

    public long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        UpdatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        DeletedAt = deletedAt;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
