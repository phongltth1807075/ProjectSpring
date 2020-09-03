package project.dto;

import project.model.Category;
import project.model.Product;
import project.util.DateTimeUtil;
import project.util.ObjectUtil;

public class ProductDTO {
    private int ProductId;
    private Object Category;
    private String ProductName;
    private double ProductPrice;
    private String Description;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private String ImageProduct;
    private int Status;

    public ProductDTO() {
    }

    private project.dto.CategoryDTO categoryDTO = new CategoryDTO();

    public ProductDTO(Product product) {
        ObjectUtil.cloneObject(this, product);
        ProductName = product.getProductName();
        ProductPrice = product.getProductPrice();
        Description = product.getDescription();
        CreatedAt = DateTimeUtil.formatDateFromLong(product.getCreatedAt());
        UpdatedAt = DateTimeUtil.formatDateFromLong(product.getUpdatedAt());
        DeletedAt = DateTimeUtil.formatDateFromLong(product.getDeletedAt());
        ImageProduct = product.getImageProduct();
        Status = product.getStatus();
        categoryDTO.setCreatedAt(DateTimeUtil.formatDateFromLong(product.getCategory().getCreatedAt()));
        categoryDTO.setDeletedAt(DateTimeUtil.formatDateFromLong(product.getCategory().getDeletedAt()));
        categoryDTO.setUpdatedAt(DateTimeUtil.formatDateFromLong(product.getCategory().getUpdatedAt()));
        categoryDTO.setCategoryId(product.getCategory().getCategoryId());
        categoryDTO.setCategoryName(product.getCategory().getCategoryName());
        categoryDTO.setDescription(product.getCategory().getDescription());
        categoryDTO.setStatus(product.getCategory().getStatus());
        Category = categoryDTO;
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

    public Object getCategory() {
        return Category;
    }

    public void setCategory(Object category) {
        Category = category;
    }
}
