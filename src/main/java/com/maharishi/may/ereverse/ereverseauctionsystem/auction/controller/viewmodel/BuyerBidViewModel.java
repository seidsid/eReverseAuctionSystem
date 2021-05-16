package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel;

import java.math.BigDecimal;

public class BuyerBidViewModel {
    private BigDecimal price;

    public BuyerBidViewModel(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
