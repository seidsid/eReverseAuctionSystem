package com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.viewmodel;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Address;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class BuyerViewModel {
    @NotEmpty
    private String username;
    @Length(min = 8,max = 255)
    private String password;
    @NotEmpty
    private String organizationName;

    @NotEmpty
    private String representativeFullName;

    private Address address;

    public BuyerViewModel(String username, String password, String organizationName,String representativeFullName,Address address) {
        this.username = username;
        this.password = password;
        this.organizationName = organizationName;
        this.representativeFullName=representativeFullName;
        this.address=address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getRepresentativeFullName() {
        return representativeFullName;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "BuyerViewModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", organizationName='" + organizationName + '\'' +
                '}';
    }
}
