package com.mtuci.parking.model.dto.parking;

import com.mtuci.parking.model.entity.parking.ParkingPlace;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationDto {
    Long id;
    ParkingPlace parkingPlace;
    Long userId;
    LocalDateTime startOfReservation;
    boolean isOwner;
}
