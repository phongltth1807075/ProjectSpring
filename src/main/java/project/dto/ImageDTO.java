package project.dto;

import project.model.Image;
import project.util.DateTimeUtil;

import java.util.List;

public class ImageDTO {
    private int id;
    private String url;
    private Image.imageStatus status;
    private int productId;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
        this.status = image.getStatus();
        this.productId = image.getProductId();
        this.createdAt = DateTimeUtil.formatDateFromLong(image.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(image.getUpdatedAt());
        this.deletedAt = DateTimeUtil.formatDateFromLong(image.getDeletedAt());
    }

    public ImageDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image.imageStatus getStatus() {
        return status;
    }

    public void setStatus(Image.imageStatus status) {
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}
