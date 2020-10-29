package com.mtuci.parking.model.dto.account;

import com.mtuci.parking.model.entity.parking.Reservation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String username;
    String authority;
    Iterable<Reservation> listOfReservations;
}
