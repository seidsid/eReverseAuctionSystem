package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String userName;

    @OneToMany
    List<Role> roles;

    public Account() {
    }

    public Account(String password, String userName) {
        this.password = password;
        this.userName = userName;
        roles = new ArrayList<>();
        roles.add(new Role());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
