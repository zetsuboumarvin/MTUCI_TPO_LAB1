package com.mtuci.parking.model.mapper;

import com.mtuci.parking.service.account.AccountService;
import com.mtuci.parking.model.dto.parking.ReservationDto;
import com.mtuci.parking.model.entity.parking.Reservation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class ReservationMapperImp implements ReservationMapper {

    private final AccountService accountService;

    public ReservationDto convertReservationToDto(Reservation reserv) {
        if (reserv != null) {
            return new ReservationDto(reserv.getId(), reserv.getParkingPlace(),
                    reserv.getUserId(), reserv.getStartOfReservation(),
                    reserv.getUserId().equals(accountService.getAccountId()));
        }
        return null;
    }
}
