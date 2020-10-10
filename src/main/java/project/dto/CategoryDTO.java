package project.dto;

import project.model.Category;
import project.util.DateTimeUtil;

public class CategoryDTO {
    private int CategoryId;
    private String CategoryName;
    private String Description;
    private long CreatedAt;
    private long UpdatedAt;
    private long DeletedAt;
    private Category.CategoryStatus Status;

    public CategoryDTO(Category category) {
        CategoryId = category.getCategoryId();
        CategoryName = category.getCategoryName();
        Description = category.getDescription();
        CreatedAt = category.getCreatedAt();
        UpdatedAt = category.getUpdatedAt();
        DeletedAt = category.getDeletedAt();
        Status = category.getStatus();
    }

    public CategoryDTO() {

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

    public Category.CategoryStatus getStatus() {
        return Status;
    }

    public void setStatus(Category.CategoryStatus status) {
        Status = status;
    }
}
