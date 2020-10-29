package com.mtuci.parking.repository.parking;

import com.mtuci.parking.model.entity.parking.ParkingPlace;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ParkingPlaceRepository extends JpaRepositoryImplementation<ParkingPlace, Long> {
}
