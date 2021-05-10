package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Oraganization extends Role{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String orgName;
    private String representativeFullName;
    private Date recordedDate;
    byte[] legalDocumentAttachement;
    Date verificaitionDate;

    public Oraganization() {
    }

    public Oraganization(String orgName, String representativeFullName, Date recordedDate, byte[] legalDocumentAttachement, Date verificaitionDate, String roleName) {
        super(roleName);
        this.orgName = orgName;
        this.representativeFullName = representativeFullName;
        this.recordedDate = recordedDate;
        this.legalDocumentAttachement = legalDocumentAttachement;
        this.verificaitionDate = verificaitionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRepresentativeFullName() {
        return representativeFullName;
    }

    public void setRepresentativeFullName(String representativeFullName) {
        this.representativeFullName = representativeFullName;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public byte[] getLegalDocumentAttachement() {
        return legalDocumentAttachement;
    }

    public void setLegalDocumentAttachement(byte[] legalDocumentAttachement) {
        this.legalDocumentAttachement = legalDocumentAttachement;
    }

    public Date getVerificaitionDate() {
        return verificaitionDate;
    }

    public void setVerificaitionDate(Date verificaitionDate) {
        this.verificaitionDate = verificaitionDate;
    }
}
