package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Bid;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;

public class SupplierItemViewModel {
    private String description;
    private SupplierBidViewModel myBid;

    public SupplierItemViewModel(Item item,String supplierUsername)
    {
        this.description=item.getSpecification();
        Bid myBid=item.getBids().stream().filter(bid -> bid.getSupplier().getAccount().getUserName().equals(supplierUsername)).findFirst().orElse(null);
        if(myBid!=null){
            this.myBid=new SupplierBidViewModel(myBid.getPrice(),item.calculateRank(myBid));
        }
    }
    public String getDescription() {
        return description;
    }

    public SupplierBidViewModel getMyBid() {
        return myBid;
    }
}
