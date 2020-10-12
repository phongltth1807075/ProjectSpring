package project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accountId;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private double totalPrice;
    private OrderStatus status;
    private String shipAddress;
    private String shipPhone;
    private int transportersId;
    private PaymentType paymentType;
    private int sellerId;

    public enum OrderStatus {
        Pending, Condirmed, Shipping, Paid, Done, Cancel, Deleted
    }

    @OneToOne()
    @JoinColumn(name = "transportersId", insertable = false, updatable = false)
    private Transporters transporters;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderDetailEntity> orderDetailEntitySet = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Accounts accounts;


    public enum PaymentType {
        Cod, InternetBanking, DirectTransfer
    }

    public OrdersEntity() {
    }


    public OrdersEntity(int id, int accountId, long createdAt, long updatedAt, long deletedAt, double totalPrice, OrderStatus status, String shipAddress, String shipPhone, int transportersId, PaymentType paymentType, int sellerId, Transporters transporters, Set<OrderDetailEntity> orderDetailEntitySet, Accounts accounts) {
        this.id = id;
        this.accountId = accountId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.totalPrice = totalPrice;
        this.status = status;
        this.shipAddress = shipAddress;
        this.shipPhone = shipPhone;
        this.transportersId = transportersId;
        this.paymentType = paymentType;
        this.sellerId = sellerId;
        this.transporters = transporters;
        this.orderDetailEntitySet = orderDetailEntitySet;
        this.accounts = accounts;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
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

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public Set<OrderDetailEntity> getOrderDetailEntitySet() {
        return orderDetailEntitySet;
    }

    public void setOrderDetailEntitySet(Set<OrderDetailEntity> orderDetailEntitySet) {
        this.orderDetailEntitySet = orderDetailEntitySet;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }


    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Transporters getTransporters() {
        return transporters;
    }

    public void setTransporters(Transporters transporters) {
        this.transporters = transporters;
    }

    public int getTransportersId() {
        return transportersId;
    }

    public void setTransportersId(int transportersId) {
        this.transportersId = transportersId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
