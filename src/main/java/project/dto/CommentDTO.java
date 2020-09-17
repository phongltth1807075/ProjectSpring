package project.dto;

import project.model.Comment;

public class CommentDTO {
    private int id;
    private int accountId;
    private String accountName;
    private int productId;
    private String comment;
    private String productName;
    private Comment.CommentStatus status;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.accountId = comment.getAccountId();
        this.accountName = comment.getAccount().getAccountName();
        this.productId = comment.getProductId();
        this.productName = comment.getProduct().getProductName();
        this.comment = comment.getComment();
        this.status = comment.getStatus();
    }

    public CommentDTO() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Comment.CommentStatus getStatus() {
        return status;
    }

    public void setStatus(Comment.CommentStatus status) {
        this.status = status;
    }
}
