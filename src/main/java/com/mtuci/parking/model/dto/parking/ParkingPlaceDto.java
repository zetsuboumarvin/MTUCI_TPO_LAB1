package com.mtuci.parking.model.dto.parking;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingPlaceDto {
    Long id;
    String address;
    String zone;
    Integer floor;
    boolean forDisabled;
    ReservationDto reservationDto;
}
