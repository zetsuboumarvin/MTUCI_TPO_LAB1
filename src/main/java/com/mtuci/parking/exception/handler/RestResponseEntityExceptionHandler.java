package com.mtuci.parking.exception.handler;

import com.mtuci.parking.exception.ParkingPlaceNotFoundException;
import com.mtuci.parking.exception.ParkingPlaceNotFreeException;
import com.mtuci.parking.exception.ReservationNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ParkingPlaceNotFoundException.class, ReservationNotFoundException.class})
    protected ResponseEntity<Object> handleConflictNotFound(Exception e, WebRequest req) {
        String body = "";
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(ParkingPlaceNotFreeException.class)
    protected ResponseEntity<Object> handleConflictForbidden(Exception e, WebRequest req) {
        String body = "";
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.FORBIDDEN, req);
    }
}
