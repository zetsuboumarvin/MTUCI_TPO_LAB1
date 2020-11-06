package com.mtuci.parking.service.account;

import com.mtuci.parking.model.entity.account.UserData;
import com.mtuci.parking.model.entity.parking.Reservation;
import com.mtuci.parking.repository.account.UserDataRepository;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceUnitTest {

    @Mock
    private UserDataRepository userDataRepository;
    @Mock
    private ReservationRepository reservationRepository;
    private AccountService accountService;

    @BeforeEach
    public void init() {
        UserData userData = new UserData();
        userData.setId(1L);
        accountService = new AccountServiceImpl(userDataRepository, reservationRepository);
        Mockito.when(userDataRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(userData));
        Mockito.when(reservationRepository.findByUserId(Mockito.anyLong())).thenReturn(List.of(new Reservation()));
    }

    @Test
    public void getAccountInfo() {
        Assertions.assertNotNull(accountService.getAccountInfo());
    }

    @Test
    public void getAccountId() {
        Assertions.assertNotNull(accountService.getAccountId());
    }

}
