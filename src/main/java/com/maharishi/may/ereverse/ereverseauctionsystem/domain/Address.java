package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;

@Embeddable
public class Address {

    private String streetNo;
    private String city;
    private String email;

    public Address() {
    }

    public Address(String streetNo, String city, String email) {
        this.streetNo = streetNo;
        this.city = city;
        this.email = email;
    }


    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
