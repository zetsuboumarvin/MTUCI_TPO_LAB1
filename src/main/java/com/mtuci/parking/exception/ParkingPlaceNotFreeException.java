package com.mtuci.parking.exception;

public class ParkingPlaceNotFreeException extends RuntimeException {
    public ParkingPlaceNotFreeException(String error) {
        super(error);
    }
}
