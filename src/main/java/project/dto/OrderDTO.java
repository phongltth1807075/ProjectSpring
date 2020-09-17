package project.dto;


import project.model.OrdersEntity;
import project.util.DateTimeUtil;
import project.util.ObjectUtil;

public class OrderDTO {
    private int id;
    private int accountId;
    private String accountName;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private double totalPrice;
    private OrdersEntity.OrderStatus status;
    private String shipAddress;
    private String shipPhone;
    private String transportersName;
    private OrdersEntity.PaymentType paymentType;


    public OrderDTO(OrdersEntity orderEntity) {
        ObjectUtil.cloneObject(this, orderEntity);
        this.id = orderEntity.getId();
        this.accountId = orderEntity.getAccountId();
        this.createdAt = DateTimeUtil.formatDateFromLong(orderEntity.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(orderEntity.getUpdatedAt());
        this.deletedAt = DateTimeUtil.formatDateFromLong(orderEntity.getDeletedAt());
        this.totalPrice = orderEntity.getTotalPrice();
        this.status = orderEntity.getStatus();
        this.shipAddress = orderEntity.getShipAddress();
        this.shipPhone = orderEntity.getShipPhone();
        this.paymentType = orderEntity.getPaymentType();
        this.accountName = orderEntity.getAccounts().getAccountName();
        this.transportersName = orderEntity.getTransporters().getTransportersName();
    }

    public OrderDTO() {
    }

    public String getTransportersName() {
        return transportersName;
    }

    public void setTransportersName(String transportersName) {
        this.transportersName = transportersName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrdersEntity.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrdersEntity.OrderStatus status) {
        this.status = status;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public OrdersEntity.PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(OrdersEntity.PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
