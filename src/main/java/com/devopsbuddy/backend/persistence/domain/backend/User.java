package com.devopsbuddy.backend.persistence.domain.backend;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable, UserDetails {

    /** The Serial Version UID for Serializable Classes*/
    private static final long serialVersionUID = 1L;

    public User(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // GeneratedValue: db automatically assigns user ids at creation
    private long id;

    private String userName;
    private String password;
    private String email;

    @Column(name = "first_name") // Column: to overwrite default values, to use these values to define column names
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Length(max = 500)
    private String description;

    private String country;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;

    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER) // ManyToOne: informs entity manager that there will be many users with the same plan
    @JoinColumn(name = "plan_id") // JoinColumn: FK -> defines column at user table that will be used as foreign key
    private Plan plan;

    // for each user id in this table,
    // there is the potential of many roles in the user role table
    // indicate many occurences of the same object

    // mappedby user identifies name that we have used in the UserRole entity for user relationship
    // give me all records in user table (cascade),
    // fetch eager, when we read a user, we want to get all their roles
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {

//        return Objects.hash(id);
        return (int) (id ^ (id >>> 32));
    }

    // no expiration implemented yet
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // no locking implemented yet
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // no credential expiration implemented yet
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // creates a set of granted authorities
    // and for each of the users in the user roles set
    // we will create a new authority class with a role name
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }
}
