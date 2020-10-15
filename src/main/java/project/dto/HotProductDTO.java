package project.dto;

import project.model.HotProducts;
import project.model.Product;

public class HotProductDTO {
    private int HotProductId;
    private int productId;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private HotProducts.HotProductStatus status;
    private ProductDTO product;

    public HotProductDTO(HotProducts hotProducts) {
        HotProductId = hotProducts.getHotProductId();
        this.productId = hotProducts.getProductId();
        this.status = hotProducts.getStatus();
        ProductDTO productDTO = new ProductDTO(hotProducts.getProduct());
        this.product = productDTO;
        this.createdAt = hotProducts.getCreatedAt();
        this.updatedAt = hotProducts.getUpdatedAt();
        this.deletedAt = hotProducts.getDeletedAt();
    }

    public HotProductDTO() {
    }

    public int getHotProductId() {
        return HotProductId;
    }

    public void setHotProductId(int hotProductId) {
        HotProductId = hotProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public HotProducts.HotProductStatus getStatus() {
        return status;
    }

    public void setStatus(HotProducts.HotProductStatus status) {
        this.status = status;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
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
}
