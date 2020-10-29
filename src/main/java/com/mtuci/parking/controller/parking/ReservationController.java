package com.mtuci.parking.controller.parking;

import com.mtuci.parking.model.dto.parking.ReservationDto;
import com.mtuci.parking.service.parking.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/{id}")
    public ReservationDto findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @GetMapping
    public Page<ReservationDto> findAll(Pageable pageable) {
        return reservationService.findAll(pageable);
    }

    @PostMapping("/{id}")
    public void save(@PathVariable Long id) {
        reservationService.save(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }

}
