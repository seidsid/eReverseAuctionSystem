package com.maharishi.may.ereverse.ereverseauctionsystem.account.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {
    private AccountService accountService;
    private JWTService jwtService;

    @Autowired
    public AccountController(AccountService accountService,JWTService jwtService) {
        this.accountService = accountService;
        this.jwtService=jwtService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String,String>> authenticate(@RequestBody Map<String,String> request)
    {
        if(!request.containsKey("username")||!request.containsKey("password"))
        {
            return ResponseEntity.badRequest().body(Map.of("reason","username and phoneNumber must not be empty"));
        }
        String username=request.get("username");
        String password=request.get("password");
        Account account;
        if((account=accountService.authenticate(username,password))==null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("result","bad credential"));
        }
        return ResponseEntity.ok(Map.of("token",jwtService.generateToken(account),"role",account.getAllRoleNames().get(0)));
    }
}
