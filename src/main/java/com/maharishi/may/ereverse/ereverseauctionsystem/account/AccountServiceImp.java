package com.maharishi.may.ereverse.ereverseauctionsystem.account;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.repository.AccountRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService{
    private AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void create(Account account){
        if(accountRepository.findByUserName(account.getUserName())!=null)
        {
            throw new DuplicateAccountException(String.format("duplicate account for %s",account.getUserName()));
        }
        accountRepository.save(account);
    }
}
