package com.maharishi.may.ereverse.ereverseauctionsystem.admin.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.SystemAdmin;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AccountService accountService;
    private OrganizationService organizationService;

    public AdminService(AccountService accountService,OrganizationService organizationService) {
        this.accountService = accountService;
        this.organizationService=organizationService;
    }
    public Account authenticate(String username, String password)
    {
        Account account=accountService.findByUsernameAndPassword(username,password);
        if(account==null||!account.hasRole("admin"))return null;
        return account;
    }
    public void activate(String adminUsername, long organizationId)
    {
        Account admin=accountService.findByUsername(adminUsername);
        Organization organization=organizationService.findOrganization(organizationId);
        organizationService.activate(organization,(SystemAdmin) accountService.findByUsername(adminUsername).getRole("admin").orElse(null));
    }
}
