package com.mtuci.parking.controller.parking;

import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.entity.parking.ParkingPlace;
import com.mtuci.parking.service.parking.ParkingPlaceService;
import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-places")
@CrossOrigin(origins = "*")
public class ParkingPlaceController {

    private final ParkingPlaceService service;

    @GetMapping
    public Page<ParkingPlaceDto> findAll(Pageable pageRequest) {
        return service.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public ParkingPlaceDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ParkingPlace place) {
        service.save(place);
    }

    @PatchMapping("/{id}")
    public void change(@PathVariable Long id, @RequestBody ParkingPlace place) {
        service.change(id, place);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/search")
    public Page<ParkingPlaceDto> search(ParkingPlaceSearchParams params, Pageable pageable) {
        return service.search(params, pageable);
    }
}
