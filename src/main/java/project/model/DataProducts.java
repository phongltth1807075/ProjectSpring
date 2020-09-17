package project.model;

import java.util.List;

public class DataProducts {
    private Product product;
    private List<Image> imageList;
    private double totalProducts;

    public DataProducts(Product product, List<Image> imageList, double totalProducts) {
        this.product = product;
        this.imageList = imageList;
        this.totalProducts = totalProducts;
    }

    public DataProducts() {
    }

    public double getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(double totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
