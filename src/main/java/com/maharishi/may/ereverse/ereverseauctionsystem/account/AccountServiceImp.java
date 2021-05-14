package com.maharishi.may.ereverse.ereverseauctionsystem.account;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.repository.AccountRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService{
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
        return accountRepository.findByUserNameAndPassword(username,password);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUserName(username);
    }
}
