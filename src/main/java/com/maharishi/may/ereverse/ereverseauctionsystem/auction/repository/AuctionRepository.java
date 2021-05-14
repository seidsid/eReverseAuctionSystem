package com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
}
