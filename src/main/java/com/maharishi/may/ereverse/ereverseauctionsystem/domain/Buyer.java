package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Buyer extends Organization {
    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL)
    private List<Auction> auctions=new ArrayList<>();
    public Buyer() {
    }

    public Buyer(String orgName, String representativeFullName, Date recordedDate,
                 byte[] legalDocumentAttachement, Date verificaitionDate, String roleName, Account account) {
        super(orgName, representativeFullName, recordedDate, legalDocumentAttachement, verificaitionDate, roleName, account);
    }
    public void addAuction(Auction auction)
    {
        this.auctions.add(auction);
        auction.setBuyer(this);
    }
}
