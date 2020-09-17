package project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
    private String accountName;
    private String phoneNumber;
    private String email;
    private String address;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private Gender gender;
    private long birthday;
    private AccountStatus status;
    private String password;
    private String token;

    public enum AccountStatus {
        Active, Deactive, Deleted
    }
    public enum Gender {
        Male, Female, Other
    }

    @OneToOne(mappedBy = "accounts")
    private Product product;

    @OneToOne(mappedBy = "account")
    private Comment comment;

    @OneToOne(mappedBy = "account")
    private Rating rating;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "accounts")
    private List<OrdersEntity> orderEntity;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "accountId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private List<Roles> rolesList;


    public Accounts() {
    }

    public Accounts(int accountId, String accountName, String phoneNumber, String email, String address, long createdAt, long updatedAt, long deletedAt, Gender gender, long birthday, AccountStatus status, String password, String token, Product product, Comment comment, Rating rating, List<OrdersEntity> orderEntity, List<Roles> rolesList) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.gender = gender;
        this.birthday = birthday;
        this.status = status;
        this.password = password;
        this.token = token;
        this.product = product;
        this.comment = comment;
        this.rating = rating;
        this.orderEntity = orderEntity;
        this.rolesList = rolesList;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrdersEntity> getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(List<OrdersEntity> orderEntity) {
        this.orderEntity = orderEntity;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }
}
