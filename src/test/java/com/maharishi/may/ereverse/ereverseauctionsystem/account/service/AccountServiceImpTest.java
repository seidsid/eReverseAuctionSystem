package com.maharishi.may.ereverse.ereverseauctionsystem.account.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.DuplicateAccountException;
import com.maharishi.may.ereverse.ereverseauctionsystem.account.repository.AccountRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceImpTest{
    @Test
    public void testCreateDuplicate(){
        AccountRepository accountRepositoryMock= Mockito.mock(AccountRepository.class);
        Account accountMock=Mockito.mock(Account.class);

        AccountServiceImp accountService=new AccountServiceImp(accountRepositoryMock);

        Mockito.when(accountMock.getUserName()).thenReturn("");
        Mockito.when(accountRepositoryMock.findByUserName(Mockito.anyString())).thenReturn(accountMock);

        assertThrows(DuplicateAccountException.class,() -> accountService.create(accountMock));
    }

    @Test
    public void testCreateNonDuplicate(){
        AccountRepository accountRepositoryMock= Mockito.mock(AccountRepository.class);
        Account accountMock=Mockito.mock(Account.class);

        AccountServiceImp accountService=new AccountServiceImp(accountRepositoryMock);

        Mockito.when(accountMock.getUserName()).thenReturn("");
        Mockito.when(accountRepositoryMock.findByUserName(Mockito.anyString())).thenReturn(null);

        accountService.create(accountMock);
    }
    @Test
    public void testAuthenticate(){
        AccountRepository accountRepositoryMock= Mockito.mock(AccountRepository.class);
        Account accountMock=Mockito.mock(Account.class);

        AccountServiceImp accountService=new AccountServiceImp(accountRepositoryMock);

        Mockito.when(accountMock.getUserName()).thenReturn("user");
        Mockito.when(accountMock.getPassword()).thenReturn("user");
        Mockito.when(accountMock.hasRole(Mockito.anyString())).thenReturn(true);

        Mockito.when(accountRepositoryMock.findByUserName("user")).thenReturn(accountMock);

        assertNotNull(accountService.authenticate("user","user"));
        assertNull(accountService.authenticate("user","ADMIN"));
    }
}