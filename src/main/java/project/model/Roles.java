package project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int RoleId;
    private String RoleName;
    private long CreatedAt;
    private long UpdatedAt;
    private long DeletedAt;
    private int Status;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rolesList", cascade = CascadeType.PERSIST)
    List<Accounts> accountsList;

    public Roles() {
    }

    public Roles(int roleId, String roleName, long createdAt, long updatedAt, long deletedAt, int status, List<Accounts> accountsList) {
        RoleId = roleId;
        RoleName = roleName;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        Status = status;
        this.accountsList = accountsList;
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

    public long getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(long createdAt) {
        CreatedAt = createdAt;
    }

    public long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        UpdatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        DeletedAt = deletedAt;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public List<Accounts> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Accounts> accountsList) {
        this.accountsList = accountsList;
    }
}
