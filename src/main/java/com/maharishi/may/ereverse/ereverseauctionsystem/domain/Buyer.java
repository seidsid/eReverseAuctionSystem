package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Buyer extends Oraganization {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "buyer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Auction> auctions = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String orgName, String representativeFullName, Date recordedDate,
                 byte[] legalDocumentAttachement, Date verificaitionDate, String roleName, Account account) {
        super(orgName, representativeFullName, recordedDate, legalDocumentAttachement, verificaitionDate, roleName, account);
    }

    public void addAuction(Auction auction){
        auctions.add(auction);
    }
    public void removeAuction(Auction auction){
        auctions.remove(auction);
    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }
}
