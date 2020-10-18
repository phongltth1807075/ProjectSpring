package project.dto;

import project.model.Image;
import project.model.Product;
import project.util.DateTimeUtil;
import project.util.ObjectUtil;

import java.util.List;

public class ProductDTO {
    private int ProductId;
    private Object Category;
    private String ProductName;
    private double ProductPrice;
    private String Description;
    private long CreatedAt;
    private long UpdatedAt;
    private long DeletedAt;
    private String ImageProduct;
    private Product.ProductStatus Status;
    private int AccountId;
    private boolean hotProductStatus;
    private double TotalProduct;

    public ProductDTO() {
    }


    public ProductDTO(Product product) {
        project.dto.CategoryDTO categoryDTO = new CategoryDTO();
        ProductId = product.getProductId();
        AccountId = product.getAccountId();
        ProductName = product.getProductName();
        ProductPrice = product.getProductPrice();
        Description = product.getDescription();
        CreatedAt = product.getCreatedAt();
        UpdatedAt = product.getUpdatedAt();
        DeletedAt = product.getDeletedAt();
        ImageProduct = product.getImageProduct();
        Status = product.getStatus();
        categoryDTO.setCreatedAt(product.getCategory().getCreatedAt());
        categoryDTO.setDeletedAt(product.getCategory().getDeletedAt());
        categoryDTO.setUpdatedAt(product.getCategory().getUpdatedAt());
        categoryDTO.setCategoryId(product.getCategory().getCategoryId());
        categoryDTO.setCategoryName(product.getCategory().getCategoryName());
        categoryDTO.setDescription(product.getCategory().getDescription());
        categoryDTO.setStatus(product.getCategory().getStatus());
        Category = categoryDTO;
        TotalProduct = product.getWarehouse().getTotalProduct();
        hotProductStatus = product.isHotProductStatus();
    }

    public boolean isHotProductStatus() {
        return hotProductStatus;
    }

    public void setHotProductStatus(boolean hotProductStatus) {
        this.hotProductStatus = hotProductStatus;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
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

    public Product.ProductStatus getStatus() {
        return Status;
    }

    public void setStatus(Product.ProductStatus status) {
        Status = status;
    }

    public Object getCategory() {
        return Category;
    }

    public void setCategory(Object category) {
        Category = category;
    }

    public double getTotalProduct() {
        return TotalProduct;
    }

    public void setTotalProduct(double totalProduct) {
        TotalProduct = totalProduct;
    }
}
