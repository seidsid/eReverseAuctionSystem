package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import com.maharishi.may.ereverse.ereverseauctionsystem.auction.ClosedAuctionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemTest {
    @Test
    public void testBidThrowsException() {
        Item item=new Item("dummy specification");
        Auction auctionMock=Mockito.mock(Auction.class);
        Bid bidMock=Mockito.mock(Bid.class);
        item.setAuction(auctionMock);
        Mockito.when(auctionMock.isOpen()).thenReturn(false);
        assertThrows(ClosedAuctionException.class,() -> item.bid(bidMock));
    }
    @Test
    public void testBidAdds(){
        Item item=new Item("dummy specification");
        Auction auctionMock=Mockito.mock(Auction.class);
        Bid bidMock=Mockito.mock(Bid.class);
        item.setAuction(auctionMock);

        Mockito.when(auctionMock.isOpen()).thenReturn(true);
        assertEquals(item.getBids().size(),0);
        item.bid(bidMock);
        assertEquals(item.getBids().size(),1);
    }
    @Test
    public void testCalculateRank(){
        Item item=new Item("dummy specification");
        Auction auctionMock=Mockito.mock(Auction.class);

        item.setAuction(auctionMock);
        Mockito.when(auctionMock.isOpen()).thenReturn(true);
        List<Bid> bidMocks=List.of(Mockito.mock(Bid.class),Mockito.mock(Bid.class),Mockito.mock(Bid.class),Mockito.mock(Bid.class));


        for(int i=0;i<bidMocks.size();i++) {
            Mockito.when(bidMocks.get(i).getPrice()).thenReturn(new BigDecimal(Integer.toString(i)));
            Mockito.when(bidMocks.get(i).getSupplierUsername()).thenReturn("sid"+i);
            item.bid(bidMocks.get(i));
            assertEquals(i+1,item.calculateRank(bidMocks.get(i)));
        }
    }
    @Test
    public void testBiddingOverAndOver(){
        Item item=new Item("dummy specification");
        Auction auctionMock=Mockito.mock(Auction.class);

        item.setAuction(auctionMock);
        Mockito.when(auctionMock.isOpen()).thenReturn(true);
        List<Bid> bidMocks=List.of(Mockito.mock(Bid.class),Mockito.mock(Bid.class),Mockito.mock(Bid.class),Mockito.mock(Bid.class));


        for(int i=0;i<bidMocks.size();i++) {
            Mockito.when(bidMocks.get(i).getPrice()).thenReturn(new BigDecimal(Integer.toString(i)));
            Mockito.when(bidMocks.get(i).getSupplierUsername()).thenReturn("sid");
            item.bid(bidMocks.get(i));
            assertEquals(1,item.calculateRank(bidMocks.get(i)));
        }
    }

    @Test
    public void testIdentifyWinner(){
        Item item=new Item("dummy specification");
        Auction auctionMock=Mockito.mock(Auction.class);

        item.setAuction(auctionMock);
        Mockito.when(auctionMock.isOpen()).thenReturn(true);
        List<Bid> bidMocks=List.of(Mockito.mock(Bid.class),Mockito.mock(Bid.class),Mockito.mock(Bid.class),Mockito.mock(Bid.class));


        for(int i=0;i<bidMocks.size();i++) {
            Mockito.when(bidMocks.get(i).getPrice()).thenReturn(new BigDecimal(Integer.toString(i)));
            Mockito.when(bidMocks.get(i).getSupplierUsername()).thenReturn("sid"+i);

            Supplier supplierMock=Mockito.mock(Supplier.class);
            Mockito.when(supplierMock.getId()).thenReturn(Long.valueOf(i));

            Mockito.when(bidMocks.get(i).getSupplier()).thenReturn(supplierMock);
            item.bid(bidMocks.get(i));
        }

        assertEquals(Long.valueOf(0),item.identifyWinner().getId());
    }
}