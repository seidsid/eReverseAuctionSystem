package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel;

import java.math.BigDecimal;

public class SupplierBidViewModel {
    private BigDecimal price;
    private long rank;

    public SupplierBidViewModel(BigDecimal price, long rank) {
        this.price = price;
        this.rank = rank;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getRank() {
        return rank;
    }
}
