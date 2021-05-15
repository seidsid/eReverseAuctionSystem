package com.maharishi.may.ereverse.ereverseauctionsystem.organization.supplier.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.DuplicateAccountException;
import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.ClosedAuctionException;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.service.AuctionService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Supplier;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.BuyerController;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.controller.MalformedObjectException;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.service.BuyerService;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.supplier.controller.viewmodel.SupplierViewModel;
import com.maharishi.may.ereverse.ereverseauctionsystem.security.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    public static Logger logger= LoggerFactory.getLogger(BuyerController.class);
    private BuyerService supplierService;
    private AccountService accountService;
    private JWTService jwtService;
    private AuctionService auctionService;

    @Autowired
    public SupplierController(BuyerService buyerService, AccountService accountService, AuctionService auctionService) {
        this.supplierService = buyerService;
        this.accountService = accountService;
        this.auctionService=auctionService;
    }
    @PostMapping
    public ResponseEntity<Map<String,String>> createAccount(@Validated @RequestBody SupplierViewModel supplierViewModel, BindingResult bindingResult)
    {
        try
        {
            logger.info("account creation attempt");
            if(bindingResult.hasErrors())
            {
                throw new MalformedObjectException(bindingResult);
            }
            logger.info("creating account for "+supplierViewModel);
            Account account=new Account(supplierViewModel.getPassword(),supplierViewModel.getUsername(), List.of(new Supplier(supplierViewModel.getOrganizationName(),supplierViewModel.getRepresentativeFullName(),new Date(),null,null,"supplier",supplierViewModel.getTinNumber(),supplierViewModel.getLicenceNumber(),null)),supplierViewModel.getAddress());
            accountService.create(account);
            return ResponseEntity.ok(Map.of("result","ok"));
        }
        catch (DuplicateAccountException duplicateAccountException)
        {
            logger.info("duplicate account for %s",supplierViewModel.getUsername());
            return ResponseEntity.badRequest().body(Map.of("result","username taken"));
        }
    }

    @PostMapping("/bid/{itemid}")
    public ResponseEntity<Map<String,String>> bid(Authentication principal, @RequestBody Map<String, String> request, @PathVariable("itemid") long itemId)
    {
        try
        {
            if(!request.containsKey("price"))
            {
                return ResponseEntity.badRequest().body(Map.of("reason","price must be available"));
            }
            BigDecimal price=new BigDecimal(request.get("price"));
            long rank=auctionService.bid(itemId,price,principal.getName());
            return ResponseEntity.ok(Map.of("rank",Long.toString(rank)));
        }
        catch (NumberFormatException nfe)
        {
            return ResponseEntity.badRequest().body(Map.of("reason","price must be a number."));
        }
        catch (ClosedAuctionException closedAuctionException)
        {
            return ResponseEntity.badRequest().body(Map.of("reason","This auction is closed!"));
        }
    }
}
