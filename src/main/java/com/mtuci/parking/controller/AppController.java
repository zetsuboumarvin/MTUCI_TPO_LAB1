package com.mtuci.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hostname")
@CrossOrigin(origins = "*")
public class AppController {

    @GetMapping()
    public ResponseEntity<String> getHostname() throws UnknownHostException {
        return new ResponseEntity<>(InetAddress.getLocalHost().getHostName(), HttpStatus.OK);
    }
}
