package com.mtuci.parking.model.mapper;

import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.entity.parking.ParkingPlace;

public interface ParkingPlaceMapper {

    ParkingPlaceDto convertParkingPlaceToDto(ParkingPlace place);

}
