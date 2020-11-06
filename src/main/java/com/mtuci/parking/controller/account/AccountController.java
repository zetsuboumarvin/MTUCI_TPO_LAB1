package com.mtuci.parking.controller.account;

import com.mtuci.parking.model.dto.account.UserDto;
import com.mtuci.parking.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<UserDto> getAccountInfo() {
        return new ResponseEntity<>(service.getAccountInfo(), HttpStatus.OK);
    }

}
