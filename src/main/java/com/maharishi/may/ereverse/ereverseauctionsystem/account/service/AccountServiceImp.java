package com.maharishi.may.ereverse.ereverseauctionsystem.account.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.DuplicateAccountException;
import com.maharishi.may.ereverse.ereverseauctionsystem.account.repository.AccountRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(Account account){
        if(exists(account.getUserName()))
        {
            throw new DuplicateAccountException(String.format("duplicate account for %s",account.getUserName()));
        }
        accountRepository.save(account);
    }

    @Override
    public boolean exists(String username) {
        return accountRepository.findByUserName(username)!=null;
    }

    @Override
    public boolean exists(String username, String password) {
        Account account = accountRepository.findByUserName(username);
        if(account==null)return false;
        return account.getPassword().equals(password);
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) {
        Account account= accountRepository.findByUserName(username);
        if(account==null)return null;
        else{
            return account.getPassword().equals(password)?account:null;
        }
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUserName(username);
    }

    @Override
    public Account authenticate(String username, String password) {
        Account account=findByUsernameAndPassword(username,password);
        if(account!=null)
        {
            if(account.hasRole("admin"))return account;
            else
            {
                Organization organization=(Organization)account.getRole("supplier").orElse(account.getRole("buyer").orElse(null));
                if(organization.isActivated())return account;
                return null;
            }
        }
        return null;
    }
}
