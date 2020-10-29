package com.mtuci.parking.service.parking;

import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import com.mtuci.parking.model.entity.parking.ParkingPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkingPlaceService {

    Page<ParkingPlaceDto> findAll(Pageable pageable);

    ParkingPlaceDto findById(Long id);

    void save(ParkingPlace place);

    void change(Long id, ParkingPlace place);

    void deleteById(Long id);

    Page<ParkingPlaceDto> search(ParkingPlaceSearchParams params, Pageable pageable);
}
