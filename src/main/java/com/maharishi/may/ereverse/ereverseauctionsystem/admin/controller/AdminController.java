package com.maharishi.may.ereverse.ereverseauctionsystem.admin.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.admin.service.AdminService;
import com.maharishi.may.ereverse.ereverseauctionsystem.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
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
