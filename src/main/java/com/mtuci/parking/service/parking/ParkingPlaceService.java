package com.mtuci.parking.service.parking;

import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkingPlaceService {

    Page<ParkingPlaceDto> findAll(Pageable pageable);

    ParkingPlaceDto findById(Long id);

    void save(ParkingPlaceDto place);

    void change(Long id, ParkingPlaceDto place);

    void deleteById(Long id);

    Page<ParkingPlaceDto> search(ParkingPlaceSearchParams params, Pageable pageable);
}
