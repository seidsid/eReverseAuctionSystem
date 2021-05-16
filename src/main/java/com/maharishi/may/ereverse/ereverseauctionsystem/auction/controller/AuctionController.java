package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel.BuyerAuctionViewModel;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller.viewmodel.SupplierAuctionViewModel;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.service.AuctionService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private AuctionService auctionService;
    private AccountService accountService;

    @Autowired
    public AuctionController(AuctionService auctionService,AccountService accountService) {
        this.auctionService = auctionService;
        this.accountService=accountService;
    }
    @GetMapping
    public Object allAuctions(Authentication principal)
    {
        Account account= accountService.findByUsername(principal.getName());
        if(account.hasRole("supplier")){
            return auctionService.getAuctionsForSupplier().stream().map(auction -> new SupplierAuctionViewModel(auction,principal.getName())).collect(Collectors.toList());
        }
        else if(account.hasRole("buyer")){
            return auctionService.getAuctionsForBuyer(account.getUserName()).stream().map(auction -> new BuyerAuctionViewModel(auction)).collect(Collectors.toList());
        }
        else {
            return ResponseEntity.badRequest().body("not supplier or buyer");
        }
    }
}
