package com.maharishi.may.ereverse.ereverseauctionsystem.admin.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.admin.service.AdminService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private JWTService jwtService;
    @Autowired
    public AdminController(AdminService adminService,JWTService jwtService) {
        this.adminService = adminService;
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
        if((account=adminService.authenticate(username,password))==null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("result","bad credential"));
        }
        return ResponseEntity.ok(Map.of("token",jwtService.generateToken(account)));
    }
    @PostMapping("/activate")
    public ResponseEntity<Map<String,String>> activate(Authentication principal, @RequestBody Map<String, String> request)
    {
        if(!request.containsKey("organizationId"))
        {
            return ResponseEntity.badRequest().body(Map.of("result","must specify both adminUser and organizationId"));
        }
        else
        {
            adminService.activate(principal.getName(),Long.parseLong(request.get("organizationId")));
            return ResponseEntity.ok(Map.of("result","ok"));
        }
    }
}
