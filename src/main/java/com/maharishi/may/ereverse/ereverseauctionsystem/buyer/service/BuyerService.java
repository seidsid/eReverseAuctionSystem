package com.maharishi.may.ereverse.ereverseauctionsystem.buyer.service;

import com.maharishi.may.ereverse.ereverseauctionsystem.buyer.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Buyer;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuyerService {
    private BuyerRepository buyerRepository;
    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }
}
