package com.mtuci.parking.repository.account;

import com.mtuci.parking.model.entity.account.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);
}
