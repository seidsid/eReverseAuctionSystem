package com.maharishi.may.ereverse.ereverseeauctionsystem.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String specification;
    private BigDecimal leastPrice;
    private Date decisionDate;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Auction auction;

    public Item() {
    }

    public Item(String specification, BigDecimal leastPrice, Date decisionDate) {
        this.specification = specification;
        this.leastPrice = leastPrice;
        this.decisionDate = decisionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getLeastPrice() {
        return leastPrice;
    }

    public void setLeastPrice(BigDecimal leastPrice) {
        this.leastPrice = leastPrice;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
