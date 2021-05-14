package com.maharishi.may.ereverse.ereverseauctionsystem.organization.supplier.controller.viewmodel;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Address;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.viewmodel.BuyerViewModel;

import javax.validation.constraints.NotEmpty;

public class SupplierViewModel extends BuyerViewModel {
    @NotEmpty
    private String tinNumber;
    @NotEmpty
    private String licenceNumber;
    public SupplierViewModel(String username, String password, String organizationName, String representativeFullName, Address address,String tinNumber,String licenceNumber) {
        super(username, password, organizationName, representativeFullName, address);
        this.tinNumber=tinNumber;
        this.licenceNumber=licenceNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }
}
