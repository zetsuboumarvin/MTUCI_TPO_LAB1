package com.mtuci.parking.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String error) {
        super(error);
    }
}

