package com.maharishi.may.ereverse.ereverseauctionsystem.auction.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.account.service.AccountService;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.ClosedAuctionException;
import com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository.ItemRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Bid;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Organization;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Supplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AuctionService {
    private ItemRepository itemRepository;
    private AccountService accountService;

    public AuctionService(ItemRepository itemRepository,AccountService accountService) {
        this.itemRepository = itemRepository;
        this.accountService=accountService;
    }

    public void bid(long itemId, BigDecimal price,String supplierUsername)
    {
        Item item=itemRepository.findById(itemId).orElse(null);
        Supplier supplier=(Supplier)accountService.findByUsername(supplierUsername).getRole("supplier").orElse(null);
        Bid bid=new Bid(price,supplier,item);
        item.bid(bid);
        itemRepository.save(item);
    }
}
