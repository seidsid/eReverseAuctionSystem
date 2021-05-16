package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerItemViewModel {
    private String description;
    private List<BuyerBidViewModel> bids;

    public BuyerItemViewModel(Item item) {
        description=item.getSpecification();
        bids=item.getBids().stream().map(bid -> new BuyerBidViewModel(bid.getPrice())).collect(Collectors.toList());
    }

    public String getDescription() {
        return description;
    }

    public List<BuyerBidViewModel> getBids() {
        return bids;
    }
}
