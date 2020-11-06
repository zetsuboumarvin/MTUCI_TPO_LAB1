package com.mtuci.parking.model.mapper;

import com.mtuci.parking.model.dto.parking.ReservationDto;
import com.mtuci.parking.model.entity.parking.ParkingPlace;
import com.mtuci.parking.model.entity.parking.Reservation;
import com.mtuci.parking.repository.parking.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ParkingPlaceMapperUnitTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ReservationMapper reservationMapper;

    @BeforeEach
    public void init() {
        ParkingPlace place = new ParkingPlace();
        Reservation reservation = new Reservation();
        ReservationDto dto = new ReservationDto(1L, place, 1L, LocalDateTime.now(), false);
        Mockito.when(reservationRepository.findByParkingPlaceId(Mockito.anyLong())).thenReturn(Optional.of(reservation));
        Mockito.when(reservationMapper.convertReservationToDto(Mockito.any(Reservation.class))).thenReturn(dto);
    }

    @Test
    public void convertParkingPlaceToDto_test() {
        ParkingPlaceMapper mapper = new ParkingPlaceMapperImp(reservationRepository, reservationMapper);
        Assertions.assertNotNull(mapper.convertParkingPlaceToDto(new ParkingPlace()));
    }
}
