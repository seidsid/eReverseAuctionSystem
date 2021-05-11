package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Auction auction;

    public Bid() {
    }

    public Bid(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
