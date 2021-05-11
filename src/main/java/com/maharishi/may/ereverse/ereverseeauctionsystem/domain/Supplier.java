package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Supplier extends Oraganization {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private boolean isLicenseRenewed;
    private String tinNumber;
    private String licenseNo;

    public Supplier() {
    }

    public Supplier(String orgName, String representativeFullName, Date recordedDate, byte[] legalDocumentAttachement, Date verificaitionDate,
                    String roleName, boolean isLicenseRenewed, String tinNumber, String licenseNo, Account account) {
        super(orgName, representativeFullName, recordedDate, legalDocumentAttachement, verificaitionDate, roleName, account);
        this.isLicenseRenewed = isLicenseRenewed;
        this.tinNumber = tinNumber;
        this.licenseNo = licenseNo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isLicenseRenewed() {
        return isLicenseRenewed;
    }

    public void setLicenseRenewed(boolean licenseRenewed) {
        isLicenseRenewed = licenseRenewed;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
}
