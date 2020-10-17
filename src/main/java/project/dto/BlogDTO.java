package project.dto;

import project.model.Blog;

public class BlogDTO {
    private int id;
    private String title;
    private String image;
    private String description;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private Blog.BlogStatus status;

    public enum BlogStatus {
        Active, DeActive, Deleted
    }

    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.image = blog.getImage();
        this.description = blog.getDescription();
        this.createdAt = blog.getCreatedAt();
        this.updatedAt = blog.getUpdatedAt();
        this.deletedAt = blog.getDeletedAt();
        this.status = blog.getStatus();
    }

    public BlogDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Blog.BlogStatus getStatus() {
        return status;
    }

    public void setStatus(Blog.BlogStatus status) {
        this.status = status;
    }
}
