package com.maharishi.may.ereverse.ereverseauctionsystem.buyer.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.account.DuplicateAccountException;
import com.maharishi.may.ereverse.ereverseauctionsystem.buyer.controller.viewmodel.BuyerViewModel;
import com.maharishi.may.ereverse.ereverseauctionsystem.buyer.service.BuyerService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Buyer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/buyer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class BuyerController {
    public static Logger logger= LoggerFactory.getLogger(BuyerController.class);
    private BuyerService buyerService;
    private AccountService accountService;

    public BuyerController(BuyerService buyerService, AccountService accountService) {
        this.buyerService = buyerService;
        this.accountService=accountService;
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
}
