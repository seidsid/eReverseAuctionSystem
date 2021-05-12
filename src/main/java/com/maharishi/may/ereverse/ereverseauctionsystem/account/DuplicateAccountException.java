package com.maharishi.may.ereverse.ereverseauctionsystem.account;

public class DuplicateAccountException extends RuntimeException{
    public DuplicateAccountException() {
    }

    public DuplicateAccountException(String message) {
        super(message);
    }

    public DuplicateAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAccountException(Throwable cause) {
        super(cause);
    }

    public DuplicateAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
