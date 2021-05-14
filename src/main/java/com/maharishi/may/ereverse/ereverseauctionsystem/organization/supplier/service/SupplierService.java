package com.maharishi.may.ereverse.ereverseauctionsystem.organization.supplier.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.AuctionRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SupplierService {
    private BuyerRepository buyerRepository;
    private AccountService accountService;
    private AuctionRepository auctionRepository;
    @Autowired
    public SupplierService(BuyerRepository buyerRepository, AccountService accountService, AuctionRepository auctionRepository) {
        this.buyerRepository = buyerRepository;
        this.accountService=accountService;
        this.auctionRepository=auctionRepository;
    }
    public Account authenticate(String username, String password)
    {
        Account account=accountService.findByUsernameAndPassword(username,password);
        if(account!=null&&account.hasRole("supplier")&&((Organization)account.getRole("supplier").orElse(null)).isActivated())return account;
        return null;
    }
}
