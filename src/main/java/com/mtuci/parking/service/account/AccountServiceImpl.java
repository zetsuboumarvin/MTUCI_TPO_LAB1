package com.mtuci.parking.service.account;

import com.mtuci.parking.exception.UsernameNotFoundException;
import com.mtuci.parking.model.entity.account.UserData;
import com.mtuci.parking.repository.account.UserDataRepository;
import com.mtuci.parking.repository.parking.ReservationRepository;
import com.mtuci.parking.model.dto.account.UserDto;
import com.mtuci.parking.model.entity.parking.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserDataRepository userRepo;
    private final ReservationRepository reservRepo;

    @Transactional(readOnly = true)
    public UserDto getAccountInfo() {
        UserData userData =  userRepo.findByUsername("user")
                .orElseThrow(() -> new UsernameNotFoundException("user"));
        return convertToDto(userData);
    }

    @Transactional(readOnly = true)
    public Long getAccountId() {
        UserData userData =  userRepo.findByUsername("user")
                .orElseThrow(() -> new UsernameNotFoundException("user"));
        return userData.getId();
    }

    private UserDto convertToDto(UserData userData) {
        Iterable<Reservation> reservs = reservRepo.findByUserId(userData.getId());
        return new UserDto(userData.getId(), userData.getUsername(), userData.getAuthority(), reservs);
    }
}
