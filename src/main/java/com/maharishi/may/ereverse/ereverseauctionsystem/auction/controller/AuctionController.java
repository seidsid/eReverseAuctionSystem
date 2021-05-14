package com.maharishi.may.ereverse.ereverseauctionsystem.auction.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.auction.ClosedAuctionException;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }
}
