package com.maharishi.may.ereverse.ereverseauctionsystem.account.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.DuplicateAccountException;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;

public interface AccountService {
    void create(Account account) throws DuplicateAccountException;
    boolean exists(String username);
    default boolean exists(String username,String password)
    {
        return findByUsernameAndPassword(username,password)!=null;
    }
    Account findByUsernameAndPassword(String username,String password);
    Account findByUsername(String username);
    Account authenticate(String username,String password);
}
