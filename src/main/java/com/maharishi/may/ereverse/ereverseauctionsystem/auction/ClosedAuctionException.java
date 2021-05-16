package com.maharishi.may.ereverse.ereverseauctionsystem.auction;

public class ClosedAuctionException extends RuntimeException{
    public ClosedAuctionException() {
    }

    public ClosedAuctionException(String message) {
        super(message);
    }

    public ClosedAuctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClosedAuctionException(Throwable cause) {
        super(cause);
    }

    public ClosedAuctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
