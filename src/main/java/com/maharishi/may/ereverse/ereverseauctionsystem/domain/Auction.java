package com.maharishi.may.ereverse.ereverseauctionsystem.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date postDate;
    private Date closureDate;

    @OneToMany(mappedBy = "auction",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Item> items;

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;

    public Auction() {
        items=new ArrayList<>();
    }

    public Auction(Date postDate, Date closureDate,List<Item> items) {
        this.postDate = postDate;
        this.closureDate = closureDate;
        this.items=items;
        items.forEach(item -> item.setAuction(this));
    }
    public boolean isOpen()
    {
        return closureDate.compareTo(new Date())==1;
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

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
