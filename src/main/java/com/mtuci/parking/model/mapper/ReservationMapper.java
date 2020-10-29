package com.mtuci.parking.model.mapper;

import com.mtuci.parking.model.dto.parking.ReservationDto;
import com.mtuci.parking.model.entity.parking.Reservation;

public interface ReservationMapper {

    ReservationDto convertReservationToDto(Reservation reserv);

}
