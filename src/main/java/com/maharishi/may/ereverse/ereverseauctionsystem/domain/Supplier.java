package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Supplier extends Organization {

    private boolean isLicenseRenewed=true;
    private String tinNumber;
    private String licenseNo;

    public Supplier() {
    }

    public Supplier(String orgName, String representativeFullName, Date recordedDate, byte[] legalDocumentAttachement, Date verificaitionDate,
                    String roleName, String tinNumber, String licenseNo, Account account) {
        super(orgName, representativeFullName, recordedDate, legalDocumentAttachement, verificaitionDate, roleName, account);
        this.tinNumber = tinNumber;
        this.licenseNo = licenseNo;
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
