package com.mtuci.parking.model.mapper;

import com.mtuci.parking.model.entity.parking.Reservation;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReservationMapperUnitTest {

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void init() {
        Mockito.when(accountService.getAccountId()).thenReturn(2L);
    }

    @Test
    public void convertReservationToDto_test() {
        ReservationMapper mapper = new ReservationMapperImp(accountService);
        Reservation reserv = new Reservation();
        reserv.setUserId(2L);
        Assertions.assertNotNull(mapper.convertReservationToDto(reserv));
    }

    @Test
    public void convertReservationToDto_test_nullReserv() {
        ReservationMapper mapper = new ReservationMapperImp(accountService);
        Reservation reserv = null;
        Assertions.assertNull(mapper.convertReservationToDto(reserv));
    }

    @Test
    public void convertReservationToDto_test_nulluserId() {
        ReservationMapper mapper = new ReservationMapperImp(accountService);
        Reservation reserv = new Reservation();
        reserv.setUserId(null);
        Assertions.assertNull(mapper.convertReservationToDto(reserv));
    }
}
