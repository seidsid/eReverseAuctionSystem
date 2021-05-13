package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "supplierId", referencedColumnName = "id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id")
    private Item item;

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

}
