package com.mtuci.parking.repository.parking;

import com.mtuci.parking.model.entity.parking.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface ReservationRepository extends JpaRepositoryImplementation<Reservation, Long> {

    Optional<Reservation> findByParkingPlaceId(Long id);

    Iterable<Reservation> findByUserId(Long id);

    Page<Reservation> findByUserId(Pageable pageable, Long id);

    boolean existsByParkingPlaceId(Long id);
}
