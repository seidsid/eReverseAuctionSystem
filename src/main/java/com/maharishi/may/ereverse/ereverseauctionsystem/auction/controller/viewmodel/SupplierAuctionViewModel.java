package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierAuctionViewModel {
    private List<SupplierItemViewModel> items;
    boolean isOpen;

    public SupplierAuctionViewModel(Auction auction, String supplierUsername) {
        items=auction.getItems().stream().map(item -> new SupplierItemViewModel(item,supplierUsername)).collect(Collectors.toList());
        isOpen=auction.isOpen();
    }
    public List<SupplierItemViewModel> getItems() {
        return items;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
