package com.mtuci.parking.exception;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String error) {
        super(error);
    }

}
