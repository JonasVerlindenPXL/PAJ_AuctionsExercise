package be.pxl.auctions.model;

import be.pxl.auctions.util.exception.InvalidBidException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Auction {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private LocalDate endDate;
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    public List<Bid> bids = new ArrayList<>();

    public Auction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        if(bid.getDate().isAfter(endDate)) {
            throw new InvalidBidException("This auction is closed.");
        }
        Bid highestBid = findHighestBid();
        if (highestBid != null && bid.getAmount() <= highestBid.getAmount()) {
            throw new InvalidBidException("Bid not allowed. Your bid should exceed €" + highestBid.getAmount());
        }
        bids.add(bid);
        bid.setAuction(this);
    }

    public boolean isFinished() {
        return endDate.isBefore(LocalDate.now());
    }

    public Bid findHighestBid() {
        if (bids.isEmpty()) {
            return null;
        }
        return Collections.max(bids, new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return Double.compare(o1.getAmount(), o2.getAmount());
            }

        });
    }
}
