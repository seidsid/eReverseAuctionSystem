package com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountServiceImp;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Buyer;
import com.maharishi.may.ereverse.ereverseauctionsystem.organization.buyer.repository.BuyerRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BuyerServiceTest {
    @Test
    public void testDoNotPostPastAuction(){
        AccountServiceImp accountServiceImp = Mockito.mock(AccountServiceImp.class);
        BuyerService buyerService=new BuyerService(accountServiceImp);

        Auction auctionMock=Mockito.mock(Auction.class);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-10);

        Mockito.when(auctionMock.getClosureDate()).thenReturn(calendar.getTime());
        assertThrows(InvalidDateException.class,() -> buyerService.postAuction(auctionMock,"foo"));
    }
    @Test
    public void testPostPastAuction(){
        AccountServiceImp accountServiceImp = Mockito.mock(AccountServiceImp.class);
        BuyerService buyerService=new BuyerService(accountServiceImp);

        Auction auctionMock=Mockito.mock(Auction.class);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,10);

        Account accountMock=Mockito.mock(Account.class);
        Mockito.when(accountServiceImp.findByUsername(Mockito.anyString())).thenReturn(accountMock);
        Mockito.when(accountMock.getRole(Mockito.anyString())).thenReturn(Optional.of(Mockito.mock(Buyer.class)));

        Mockito.when(auctionMock.getClosureDate()).thenReturn(calendar.getTime());
        buyerService.postAuction(auctionMock,"foo");
    }
}