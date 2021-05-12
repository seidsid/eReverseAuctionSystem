package com.maharishi.may.ereverse.ereverseauctionsystem.account.repository;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findByUserName(String username);
}
