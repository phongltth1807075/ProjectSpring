package project.dto;

import project.model.Transporters;
import project.util.DateTimeUtil;

public class TransportersDTO {
    private int transportersId;
    private String transportersName;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private Transporters.TransportersStatus status;

    public TransportersDTO(Transporters transporters) {
        this.transportersId = transporters.getTransportersId();
        this.transportersName = transporters.getTransportersName();
        this.createdAt = DateTimeUtil.formatDateFromLong(transporters.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(transporters.getUpdatedAt());
        this.deletedAt = DateTimeUtil.formatDateFromLong(transporters.getDeletedAt());
        this.description = transporters.getDescription();
        this.status = transporters.getStatus();
    }

    public TransportersDTO() {
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

    public Transporters.TransportersStatus getStatus() {
        return status;
    }

    public void setStatus(Transporters.TransportersStatus status) {
        this.status = status;
    }
}
