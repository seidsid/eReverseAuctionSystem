package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerAuctionViewModel {
    private List<BuyerItemViewModel> items;

    public BuyerAuctionViewModel(Auction auction) {
        items=auction.getItems().stream().map(item -> new BuyerItemViewModel(item)).collect(Collectors.toList());
    }

    public List<BuyerItemViewModel> getItems() {
        return items;
    }
}
