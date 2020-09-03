package project.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AccountId;
    private String AccountName;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private long CreatedAt;
    private long UpdatedAt;
    private long DeletedAt;
    private int Gender;
    private long Birthday;
    private int Status;
    private String Password;
    private String Token;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "AccountId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId")
    )
    private List<Roles> rolesList;


    public Accounts() {
    }

    public Accounts(int accountId, String accountName, String phoneNumber, String email, String address, long createdAt, long updatedAt, long deletedAt, int gender, long birthday, int status, String password, String token, List<Roles> rolesList) {
        AccountId = accountId;
        AccountName = accountName;
        PhoneNumber = phoneNumber;
        Email = email;
        Address = address;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        Gender = gender;
        Birthday = birthday;
        Status = status;
        Password = password;
        Token = token;
        this.rolesList = rolesList;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public long getBirthday() {
        return Birthday;
    }

    public void setBirthday(long birthday) {
        Birthday = birthday;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

}
