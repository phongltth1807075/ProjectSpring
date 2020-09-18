package project.dto;

import project.model.CommentRating;

public class CommentRatingDTO {
    private int id;
    private int accountId;
    private String accountName;
    private int productId;
    private String productName;
    private int value;
    private String comment;
    private CommentRating.CommentRatingStatus status;

    public CommentRatingDTO(CommentRating commentRating) {
        this.id = commentRating.getId();
        this.accountId = commentRating.getAccountId();
        this.accountName = commentRating.getAccounts().getAccountName();
        this.productId = commentRating.getProductId();
        this.productName = commentRating.getProduct().getProductName();
        this.value = commentRating.getValue();
        this.comment = commentRating.getComment();
        this.status = commentRating.getStatus();
    }

    public CommentRatingDTO() {
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentRating.CommentRatingStatus getStatus() {
        return status;
    }

    public void setStatus(CommentRating.CommentRatingStatus status) {
        this.status = status;
    }
}
