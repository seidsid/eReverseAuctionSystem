package com.maharishi.may.ereverse.ereverseauctionsystem.auction.repository;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
}
