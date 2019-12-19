package com.devopsbuddy.backend.persistence.domain.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_role") // changing from default table name
public class UserRole implements Serializable{

    /** The Serial Version UID for Serializable Classes*/
    private static final long serialVersionUID = 1L;

    // foreign key to user table
//    @Id // change to FetchType.LAZY or execution of TEST will result in stackoverflow
    @ManyToOne(fetch = FetchType.EAGER) // many user ids linked to a single id in the user table
    @JoinColumn(name = "user_id")
    private User user;

    // foreign key to role table
//    @Id // change to FetchType.LAZY or execution of test will result in stackoverflow
    @ManyToOne(fetch = FetchType.EAGER) // many role ids linked to a single id in the role table
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole(User user, Role role){
        this.user = user;
        this.role = role;
    }

    public UserRole(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
