package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import com.maharishi.may.ereverse.ereverseauctionsystem.auction.ClosedAuctionException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String specification;
    private BigDecimal leastPrice;
    private Date decisionDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    @ManyToOne
    private Auction auction;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    private List<Bid> bids=new ArrayList<>();

    public Item() {
    }

    public Item(String specification) {
        this.specification = specification;
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

    public void bid(Bid bid)throws ClosedAuctionException
    {
        if(!getAuction().isOpen())throw new ClosedAuctionException("auction is close");
        this.bids.add(bid);
    }
}
