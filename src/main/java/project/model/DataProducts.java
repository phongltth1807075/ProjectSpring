package project.model;

import java.util.List;

public class DataProducts {
    private Product product;
    private List<Image> imageList;

    public DataProducts(Product product, List<Image> imageList) {
        this.product = product;
        this.imageList = imageList;
    }

    public DataProducts() {
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
