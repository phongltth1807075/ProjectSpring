package project.model;

import javax.persistence.*;

@Entity
public class Transporters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transportersId;
    private String transportersName;
    private String description;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private TransportersStatus status;

    public enum TransportersStatus {
        Active, Deactive, Deleted
    }

    @OneToOne(mappedBy = "transporters")
    private OrdersEntity ordersEntity;

    public Transporters() {
    }

    public Transporters(int transportersId, String transportersName, String description, long createdAt, long updatedAt, long deletedAt, TransportersStatus status, OrdersEntity ordersEntity) {
        this.transportersId = transportersId;
        this.transportersName = transportersName;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
        this.ordersEntity = ordersEntity;
    }

    public int getTransportersId() {
        return transportersId;
    }

    public void setTransportersId(int transportersId) {
        this.transportersId = transportersId;
    }

    public String getTransportersName() {
        return transportersName;
    }

    public void setTransportersName(String transportersName) {
        this.transportersName = transportersName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TransportersStatus getStatus() {
        return status;
    }

    public void setStatus(TransportersStatus status) {
        this.status = status;
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }
}
