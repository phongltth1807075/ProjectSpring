package project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;
    private String CategoryName;
    private String Description;
    private long CreatedAt;
    private long UpdatedAt;
    private long DeletedAt;
    private int Status;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String categoryName, String description, long createdAt, long updatedAt, long deletedAt, int status, List<Product> products) {
        CategoryName = categoryName;
        Description = description;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        Status = status;
        this.products = products;
    }

    public Category() {
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
