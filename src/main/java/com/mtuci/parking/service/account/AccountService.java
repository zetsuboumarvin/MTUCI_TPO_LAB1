package com.mtuci.parking.service.account;

import com.mtuci.parking.model.dto.account.UserDto;

public interface AccountService {

    UserDto getAccountInfo();

    Long getAccountId();

}
