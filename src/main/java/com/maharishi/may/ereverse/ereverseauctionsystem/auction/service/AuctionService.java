package com.maharishi.may.ereverse.ereverseauctionsystem.auction.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.AuctionRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.ItemRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Bid;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Supplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class AuctionService {
    private ItemRepository itemRepository;
    private AccountService accountService;
    private AuctionRepository auctionRepository;

    public AuctionService(ItemRepository itemRepository,AccountService accountService,AuctionRepository auctionRepository) {
        this.itemRepository = itemRepository;
        this.accountService=accountService;
        this.auctionRepository=auctionRepository;
    }
    //returns the rank
    public long bid(long itemId, BigDecimal price,String supplierUsername)
    {
        Item item=itemRepository.findById(itemId).orElse(null);
        Supplier supplier=(Supplier)accountService.findByUsername(supplierUsername).getRole("supplier").orElse(null);
        Bid bid=new Bid(price,supplier,item);
        item.bid(bid);
        itemRepository.save(item);
        return item.calculateRank(bid);
    }
    public List<Auction> getAuctionsForBuyer(String buyerUsername){
        return StreamSupport.stream(auctionRepository.findAll().spliterator(), false).peek(auction -> {
            auction.getItems().forEach(item -> item.identifyWinner());
        }).filter(auction ->auction.getBuyer().getAccount().getUserName().equals(buyerUsername)).sorted(this::auctionSorter).collect(Collectors.toList());
    }
    private int auctionSorter(Auction o1, Auction o2){
        if(o1.isOpen()==o2.isOpen()){
            return o1.getPostDate().compareTo(o2.getClosureDate());
        }
        else{
            if(o1.isOpen())return -1;
            return 1;
        }
    }
    public List<Auction> getAuctionsForSupplier()
    {
        return StreamSupport.stream(auctionRepository.findAll().spliterator(), false).peek(auction -> {
            auction.getItems().forEach(item -> item.identifyWinner());
        }).sorted(this::auctionSorter).collect(Collectors.toList());
    }
}
