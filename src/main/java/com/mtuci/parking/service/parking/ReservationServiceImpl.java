package com.mtuci.parking.service.parking;

import com.mtuci.parking.exception.ParkingPlaceNotFoundException;
import com.mtuci.parking.exception.ParkingPlaceNotFreeException;
import com.mtuci.parking.exception.ReservationNotFoundException;
import com.mtuci.parking.repository.parking.ParkingPlaceRepository;
import com.mtuci.parking.repository.parking.ReservationRepository;
import com.mtuci.parking.service.account.AccountService;
import com.mtuci.parking.model.dto.account.UserDto;
import com.mtuci.parking.model.dto.parking.ReservationDto;
import com.mtuci.parking.model.entity.parking.Reservation;
import com.mtuci.parking.model.mapper.ReservationMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Data
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final AccountService accountService;
    private final ParkingPlaceRepository parkingPlaceRepository;

    @Transactional(readOnly = true)
    public Page<ReservationDto> findAll(Pageable pageable) {
        UserDto user = accountService.getAccountInfo();
        if (user.getAuthority().equals("ADMIN")) {
            return reservationRepository.findAll(pageable).map(reservationMapper::convertReservationToDto);
        } else {
            return reservationRepository.findByUserId(pageable, user.getId())
                    .map(reservationMapper::convertReservationToDto);
        }
    }

    @Transactional(readOnly = true)
    public ReservationDto findById(Long id) {
        return reservationRepository.findById(id).map(reservationMapper::convertReservationToDto)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation: " + id));
    }

    @Transactional
    public void save(Long parkingPlaceId) {
        if (reservationRepository.existsByParkingPlaceId(parkingPlaceId)) {
            throw new ParkingPlaceNotFreeException("Parking place already reserved: " + parkingPlaceId);
        } else {
            reservationRepository.save(Reservation.builder()
                                        .parkingPlace(parkingPlaceRepository.findById(parkingPlaceId)
                                                .orElseThrow(() -> new ParkingPlaceNotFoundException("Not found")))
                                        .userId(accountService.getAccountId())
                                        .startOfReservation(LocalDateTime.now())
                                        .build());
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation: " + id));
        if (!reservation.getUserId().equals(accountService.getAccountId()))
            throw new ReservationNotFoundException("User don't have this reservation");
        reservationRepository.deleteById(id);
    }
}
