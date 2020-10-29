package com.mtuci.parking.model.mapper;

import com.mtuci.parking.model.entity.parking.ParkingPlace;
import com.mtuci.parking.repository.parking.ReservationRepository;
import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.entity.parking.Reservation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Data
@RequiredArgsConstructor
@Component
public class ParkingPlaceMapperImp implements ParkingPlaceMapper {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Transactional(readOnly = true)
    public ParkingPlaceDto convertParkingPlaceToDto(ParkingPlace place) {
        Reservation reserv = reservationRepository.findByParkingPlaceId(place.getId()).orElse(null);
        return new ParkingPlaceDto(place.getId(), place.getAddress(), place.getZone(),
                place.getFloor(), place.isForDisabled(), reservationMapper.convertReservationToDto(reserv));
    }
}
