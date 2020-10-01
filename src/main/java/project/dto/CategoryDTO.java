package project.dto;

import project.model.Category;
import project.util.DateTimeUtil;

public class CategoryDTO {
    private int CategoryId;
    private String CategoryName;
    private String Description;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private Category.CategoryStatus Status;

    public CategoryDTO(Category category) {
        CategoryId = category.getCategoryId();
        CategoryName = category.getCategoryName();
        Description = category.getDescription();
        CreatedAt = DateTimeUtil.formatDateFromLong(category.getCreatedAt());
        UpdatedAt = DateTimeUtil.formatDateFromLong(category.getUpdatedAt());
        DeletedAt = DateTimeUtil.formatDateFromLong(category.getDeletedAt());
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

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        DeletedAt = deletedAt;
    }

    public Category.CategoryStatus getStatus() {
        return Status;
    }

    public void setStatus(Category.CategoryStatus status) {
        Status = status;
    }
}
