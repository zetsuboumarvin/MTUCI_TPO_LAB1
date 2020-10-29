package com.mtuci.parking.service.parking;

import com.mtuci.parking.model.dto.parking.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {

    ReservationDto findById(Long id);

    Page<ReservationDto> findAll(Pageable pageable);

    void save(Long parkingPlaceId);

    void deleteById(Long id);
}
