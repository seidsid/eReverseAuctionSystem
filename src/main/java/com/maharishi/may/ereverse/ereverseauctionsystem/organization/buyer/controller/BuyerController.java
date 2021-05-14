package com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.account.DuplicateAccountException;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Buyer;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.viewmodel.AuctionViewModel;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.viewmodel.BuyerViewModel;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.service.BuyerService;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.service.InvalidDateException;
import com.maharishi.may.ereverse.ereverseauctionsystem.security.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/buyer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class BuyerController {
    public static Logger logger= LoggerFactory.getLogger(BuyerController.class);
    private BuyerService buyerService;
    private AccountService accountService;
    private JWTService jwtService;

    public BuyerController(BuyerService buyerService, AccountService accountService, JWTService jwtService) {
        this.buyerService = buyerService;
        this.accountService=accountService;
        this.jwtService=jwtService;
    }
    @PostMapping
    public ResponseEntity<Map<String,String>> createAccount(@Validated @RequestBody BuyerViewModel buyerViewModel, BindingResult bindingResult)
    {
        try
        {
            logger.info("account creation attempt");
            if(bindingResult.hasErrors())
            {
                throw new MalformedObjectException(bindingResult);
            }
            logger.info("creating account for "+buyerViewModel);
            Account account=new Account(buyerViewModel.getPassword(),buyerViewModel.getUsername(), List.of(new Buyer(buyerViewModel.getOrganizationName(),buyerViewModel.getRepresentativeFullName(),new Date(),null,null,"buyer",null)),buyerViewModel.getAddress());
            accountService.create(account);
            return ResponseEntity.ok(Map.of("result","ok"));
        }
        catch (DuplicateAccountException duplicateAccountException)
        {
            logger.info("duplicate account for %s",buyerViewModel.getUsername());
            return ResponseEntity.badRequest().body(Map.of("result","username taken"));
        }
    }
    @PostMapping("/auction")
    public ResponseEntity<Map<String,String>> postAuction(Authentication principal, @RequestBody @Validated AuctionViewModel auctionViewModel, BindingResult bindingResult)
    {
        try
        {
            if(!bindingResult.hasErrors())
            {
                buyerService.postAuction(new Auction(new Date(),auctionViewModel.getClosureDate(),auctionViewModel.getSpecification().stream().map(Item::new).collect(Collectors.toList())),principal.getName());
                return ResponseEntity.ok(Map.of("result","posted"));
            }
            else
            {
                throw new MalformedObjectException(bindingResult);
            }
        }
        catch (InvalidDateException e)
        {
            return ResponseEntity.badRequest().body(Map.of("result","Date must be in the future"));
        }
    }
}
