package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SystemAdmin extends Employee{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String qualification;

    public SystemAdmin() {
    }

    public SystemAdmin(String firstName, String middleName, String lastName, Date birthDate, String sex, String qualification, String roleName) {
        super(firstName, middleName, lastName, birthDate, sex, roleName);
        this.qualification = qualification;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
