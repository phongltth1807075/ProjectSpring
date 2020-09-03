package project.dto;

import project.model.Roles;
import project.util.DateTimeUtil;

public class RoleDTO {
    private int RoleId;
    private String RoleName;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private int Status;

    public RoleDTO() {
    }

    public RoleDTO(Roles roles) {
        RoleId = roles.getRoleId();
        RoleName = roles.getRoleName();
        CreatedAt = DateTimeUtil.formatDateFromLong(roles.getCreatedAt());
        UpdatedAt = DateTimeUtil.formatDateFromLong(roles.getUpdatedAt());
        DeletedAt = DateTimeUtil.formatDateFromLong(roles.getDeletedAt());
        Status = roles.getStatus();
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
