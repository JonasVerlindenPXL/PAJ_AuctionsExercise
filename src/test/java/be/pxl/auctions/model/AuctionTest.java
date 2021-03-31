package be.pxl.auctions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuctionTest {

    @Mock
    private Bid bid;

    @InjectMocks
    private Auction auction;

    @BeforeEach
    public void init() {
        auction = new Auction();
        auction.setDescription("test");
        auction.setEndDate(LocalDate.of(2020, 4, 1));
        auction.setId(1);
    }

    @Test
    public void returnsTrueWhenAuctionEnded() {
        assertTrue(auction.isFinished());
    }

    @Test
    public void shouldReturnHighestBid() {
        List<Bid> bids = new ArrayList<Bid>();
        bids.add(new Bid(null, LocalDate.now(), 10));
        bids.add(new Bid(null, LocalDate.now(), 100));
        bids.add(new Bid(null, LocalDate.now(), 12));
        bids.add(new Bid(null, LocalDate.now(), 11));
        auction.bids = bids;
        assertEquals(100, auction.findHighestBid().getAmount());

    }
}
