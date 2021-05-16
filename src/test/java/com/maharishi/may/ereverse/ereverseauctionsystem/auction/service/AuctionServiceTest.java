package com.maharishi.may.ereverse.ereverseauctionsystem.auction.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountServiceImp;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.AuctionRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.ItemRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Bid;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AuctionServiceTest {
    @Test
    public void testAuctionRank(){
        ItemRepository itemRepositoryMock= Mockito.mock(ItemRepository.class);
        AccountServiceImp accountServiceImpMock=Mockito.mock(AccountServiceImp.class);
        AuctionRepository auctionRepository=Mockito.mock(AuctionRepository.class);

        Mockito.when(accountServiceImpMock.findByUsername(Mockito.anyString())).thenReturn(new Account());
        Item itemMock=Mockito.mock(Item.class);
        Mockito.when(itemRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(itemMock));

        AuctionService auctionService=new AuctionService(itemRepositoryMock,accountServiceImpMock,auctionRepository);
        auctionService.bid(1,new BigDecimal("19"),"hello");
        Mockito.verify(itemRepositoryMock).save(Mockito.any(Item.class));
        Mockito.verify(itemMock).calculateRank(Mockito.any(Bid.class));

    }
}