package com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository;

import org.springframework.data.repository.CrudRepository;
import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
}
