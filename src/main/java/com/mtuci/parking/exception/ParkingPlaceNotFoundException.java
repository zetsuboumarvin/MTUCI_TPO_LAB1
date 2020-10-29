package com.mtuci.parking.exception;

public class ParkingPlaceNotFoundException extends RuntimeException {

    public ParkingPlaceNotFoundException(String error) {
        super(error);
    }
}
