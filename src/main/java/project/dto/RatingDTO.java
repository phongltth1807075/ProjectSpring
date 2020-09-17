package project.dto;

import project.model.Comment;
import project.model.Rating;

public class RatingDTO {
    private int id;
    private int accountId;
    private String accountName;
    private int productId;
    private int value;
    private String productName;
    private Rating.RatingStatus status;

    public RatingDTO(Rating rating) {
        this.id = rating.getId();
        this.accountId = rating.getAccountId();
        this.accountName = rating.getAccount().getAccountName();
        this.productId = rating.getProductId();
        this.value = rating.getValue();
        this.productName = rating.getProduct().getProductName();
        this.status = rating.getStatus();
    }

    public RatingDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Rating.RatingStatus getStatus() {
        return status;
    }

    public void setStatus(Rating.RatingStatus status) {
        this.status = status;
    }
}
