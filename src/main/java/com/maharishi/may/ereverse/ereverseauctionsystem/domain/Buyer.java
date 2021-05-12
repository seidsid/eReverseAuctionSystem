package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Buyer extends Organization {

    public Buyer() {
    }

    public Buyer(String orgName, String representativeFullName, Date recordedDate,
                 byte[] legalDocumentAttachement, Date verificaitionDate, String roleName, Account account) {
        super(orgName, representativeFullName, recordedDate, legalDocumentAttachement, verificaitionDate, roleName, account);
    }
}
