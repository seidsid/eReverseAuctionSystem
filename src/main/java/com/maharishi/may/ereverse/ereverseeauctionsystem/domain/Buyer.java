package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Buyer extends Oraganization {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Buyer() {
    }

    public Buyer(String orgName, String representativeFullName, Date recordedDate, byte[] legalDocumentAttachement, Date verificaitionDate, String roleName) {
        super(orgName, representativeFullName, recordedDate, legalDocumentAttachement, verificaitionDate, roleName);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
