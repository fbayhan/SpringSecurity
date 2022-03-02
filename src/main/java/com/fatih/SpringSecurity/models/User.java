package com.fatih.SpringSecurity.models;


import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userName;
    private String emailAdress;
    private String name;
    private String surname;
    private Date createdDate;
    private Boolean isActive;

    private String password;

    private Boolean mailConfirmed;
    private Boolean forgetPassword;
    private UUID uuidCode;

    private Date uuidExpirationDate;

    private Boolean passwordUsable;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "user")
    private Set<Posts> items = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "postIs")
    )
    private Set<Posts> likedPosts = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Roles> roles = new HashSet<>();

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Posts> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Posts> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public Set<Posts> getItems() {
        return items;
    }

    public void setItems(Set<Posts> items) {
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String email) {
        this.emailAdress = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getMailConfirmed() {
        return mailConfirmed;
    }

    public void setMailConfirmed(Boolean mailConfirmed) {
        this.mailConfirmed = mailConfirmed;
    }

    public Boolean getForgetPassword() {
        return forgetPassword;
    }

    public void setForgetPassword(Boolean forgetPassword) {
        this.forgetPassword = forgetPassword;
    }

    public UUID getUuidCode() {
        return uuidCode;
    }

    public void setUuidCode(UUID uuidCode) {
        this.uuidCode = uuidCode;
    }

    public Date getUuidExpirationDate() {
        return uuidExpirationDate;
    }

    public void setUuidExpirationDate(Date uuidExpirationDate) {


        this.uuidExpirationDate = Date.from(uuidExpirationDate.toInstant().plus(Duration.ofHours(2)));
    }

    public Boolean getPasswordUsable() {
        return passwordUsable;
    }

    public void setPasswordUsable(Boolean passwordUsable) {
        this.passwordUsable = passwordUsable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + emailAdress + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", createdDate=" + createdDate +
                ", isActive=" + isActive +
                ", password='" + password + '\'' +
                ", mailConfirmed=" + mailConfirmed +
                ", forgetPassword=" + forgetPassword +
                ", uuidCode=" + uuidCode +
                ", uuidExpirationDate=" + uuidExpirationDate +
                ", passwordUsable=" + passwordUsable +
                ", address=" + address +
                ", items=" + items +
                ", likedPosts=" + likedPosts +
                ", roles=" + roles +
                '}';
    }
}
