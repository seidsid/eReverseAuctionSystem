package com.maharishi.may.ereverse.ereverseauctionsystem.security;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountUserDetailsService implements UserDetailsService
{
    private final AccountService accountService;

    @Autowired
    public AccountUserDetailsService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Account user=accountService.findByUsername(username);
        if(user!=null)
        {
            org.springframework.security.core.userdetails.User.UserBuilder builder=org.springframework.security.core.userdetails.User.withUsername(username);
            return builder.password(user.getPassword()).authorities(user.getAllRoleNames().toArray(String[]::new)).build();
        }
        else throw new UsernameNotFoundException("username not found for username "+username);
    }
}
