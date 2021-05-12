package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SystemAdmin extends Employee{

    private String qualification;

    public SystemAdmin() {
    }

    public SystemAdmin(String firstName, String middleName, String lastName, Date birthDate, String sex, String qualification, String roleName, Account account) {
        super(firstName, middleName, lastName, birthDate, sex, roleName, account);
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
