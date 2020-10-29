package com.mtuci.parking.service.parking;

import com.mtuci.parking.model.dto.account.UserDto;
import com.mtuci.parking.model.dto.parking.ReservationDto;
import com.mtuci.parking.model.entity.parking.ParkingPlace;
import com.mtuci.parking.model.entity.parking.Reservation;
import com.mtuci.parking.model.mapper.ReservationMapper;
import com.mtuci.parking.exception.ParkingPlaceNotFreeException;
import com.mtuci.parking.exception.ReservationNotFoundException;
import com.mtuci.parking.repository.parking.ParkingPlaceRepository;
import com.mtuci.parking.repository.parking.ReservationRepository;
import com.mtuci.parking.service.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReservationServiceUnitTest {

    @Mock
    private ParkingPlaceRepository parkingPlaceRepository;
    @Mock
    private ReservationMapper reservationMapper;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private AccountService accountService;
    private ReservationService service;

    @BeforeEach
    public void init() {
        this.service = new ReservationServiceImpl(reservationRepository, reservationMapper, accountService, parkingPlaceRepository);
        ParkingPlace place = new ParkingPlace();
        Reservation reservation = new Reservation();
        reservation.setUserId(1L);
        ReservationDto dto = new ReservationDto(1L, place, 1L, LocalDateTime.now(), false);
        Page<Reservation> result = new PageImpl<>(List.of(reservation));
        Mockito.when(reservationMapper.convertReservationToDto(Mockito.any(Reservation.class))).thenReturn(dto);
        Mockito.when(reservationRepository.findAll(Mockito.any(Pageable.class))).thenReturn(result);
        Mockito.when(reservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reservation));
        Mockito.when(reservationRepository.findById(-1L)).thenReturn(Optional.empty());
        Mockito.when(reservationRepository.findByUserId(Mockito.any(Pageable.class), Mockito.anyLong())).thenReturn(result);
        Mockito.when(reservationRepository.existsByParkingPlaceId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(reservationRepository.existsByParkingPlaceId(-1L)).thenReturn(false);
        Mockito.when(parkingPlaceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(place));
    }

    @Test
    public void findAll_byAdmin() {
        Mockito.when(accountService.getAccountInfo()).thenReturn(new UserDto(1L, "test", "ADMIN", null));
        assert service.findAll(PageRequest.of(1, 1)) != null;
    }

    @Test
    public void findAll_byUser() {
        Mockito.when(accountService.getAccountInfo()).thenReturn(new UserDto(1L, "test", "USER", null));
        assert service.findAll(PageRequest.of(1, 1)) != null;
    }

    @Test
    public void findById() {
        assert service.findById(Mockito.anyLong()) != null;
    }

    @Test
    public void findById_whenEmptyResponse_getReservationNotFoundException() {
        Exception ex = Assertions.assertThrows(ReservationNotFoundException.class, () -> service.findById(-1L));
        assert ex.getMessage().contains("Reservation: ");
    }

    @Test
    public void existsByParkingPlaceId_whenExist_getParkingPlaceNotFreeException() {
        Exception ex = Assertions.assertThrows(ParkingPlaceNotFreeException.class, () -> service.save(Mockito.anyLong()));
        assert ex.getMessage().contains("Parking place already reserved: ");
    }

    @Test
    public void existsByParkingPlaceId_noExceptions() {
        service.save(-1L);
    }

    @Test
    public void deleteById_whenNotOwner_getReservationNotFoundException() {
        Mockito.when(accountService.getAccountId()).thenReturn(2L);
        Exception ex = Assertions.assertThrows(ReservationNotFoundException.class, () -> service.deleteById(Mockito.anyLong()));
        assert ex.getMessage().contains("User don't have this reservation");
    }

    @Test
    public void deleteById_whenNotOwner_getNoException() {
        Mockito.when(accountService.getAccountId()).thenReturn(1L);
        service.deleteById(Mockito.anyLong());
    }

}
