package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date postDate;
    private Date closureDate;

    @OneToMany
    List<Item> items;

    public Auction() {
    }

    public Auction(Date postDate, Date closureDate) {
        this.postDate = postDate;
        this.closureDate = closureDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        this.closureDate = closureDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
