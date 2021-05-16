package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Organization extends Role{

    private String orgName;

    @Column(name = "fullName")
    private String representativeFullName;
    private Date recordedDate;

    @Column(name = "attachment")
    byte[] legalDocumentAttachement;

    @Temporal(TemporalType.TIMESTAMP)
    Date verificaitionDate;

    @ManyToOne
    private SystemAdmin sysAdmin;

    public Organization() {
    }

    public Organization(String orgName, String representativeFullName, Date recordedDate, byte[] legalDocumentAttachement, Date verificaitionDate, String roleName, Account account) {
        super(account,roleName);
        this.orgName = orgName;
        this.representativeFullName = representativeFullName;
        this.recordedDate = recordedDate;
        this.legalDocumentAttachement = legalDocumentAttachement;
        this.verificaitionDate = verificaitionDate;
    }
    public boolean isActivated()
    {
        return sysAdmin!=null;
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

    public SystemAdmin getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(SystemAdmin sysAdmin) {
        this.sysAdmin = sysAdmin;
    }
}
