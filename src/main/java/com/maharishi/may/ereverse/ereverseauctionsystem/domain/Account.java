package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String password;
    @Column(unique = true)
    private String userName;

    @OneToMany(mappedBy = "account",cascade = CascadeType.PERSIST)
    List<Role> roles;

    @Embedded
    private Address address;

    public Account() {
    }

    public Account(String password, String userName,List<Role> roles,Address address) {
        this.password = password;
        this.userName = userName;
        this.address=address;
        if(roles==null||roles.isEmpty())
        {
            throw new RuntimeException("roles must not be empty");
        }
        roles.forEach(r->r.setAccount(this));
        this.roles=roles;
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
