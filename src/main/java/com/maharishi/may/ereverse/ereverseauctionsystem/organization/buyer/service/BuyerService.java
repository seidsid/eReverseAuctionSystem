package com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.AuctionRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Buyer;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BuyerService {
    private BuyerRepository buyerRepository;
    private AccountService accountService;
    private AuctionRepository auctionRepository;
    @Autowired
    public BuyerService(BuyerRepository buyerRepository, AccountService accountService, AuctionRepository auctionRepository) {
        this.buyerRepository = buyerRepository;
        this.accountService=accountService;
        this.auctionRepository=auctionRepository;
    }
    public Account authenticate(String username, String password)
    {
        Account account=accountService.findByUsernameAndPassword(username,password);
        if(account!=null&&account.hasRole("buyer")&&((Organization)account.getRole("buyer").orElse(null)).isActivated())return account;
        return null;
    }
    public void postAuction(Auction auction,String buyerUsername)
    {
        if(auction.getClosureDate().compareTo(new Date())<1)throw new InvalidDateException("date must not be in the past!");
        Buyer buyer=((Buyer)accountService.findByUsername(buyerUsername).getRole("buyer").orElse(null));
        buyer.addAuction(auction);
    }
}
