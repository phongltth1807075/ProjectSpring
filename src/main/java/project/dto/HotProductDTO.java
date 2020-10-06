package project.dto;

import project.model.HotProducts;
import project.model.Product;

public class HotProductDTO {
    private int HotProductId;
    private int productId;
    private HotProducts.HotProductStatus status;
    private ProductDTO product;

    public HotProductDTO(HotProducts hotProducts) {
        HotProductId = hotProducts.getHotProductId();
        this.productId = hotProducts.getProductId();
        this.status = hotProducts.getStatus();
        ProductDTO productDTO = new ProductDTO(hotProducts.getProduct());
        this.product = productDTO;
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
}
