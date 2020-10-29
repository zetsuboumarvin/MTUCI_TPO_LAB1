package com.mtuci.parking.model.dto.parking;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingPlaceSearchParams {
    Long id;
    String address;
    String zone;
    Integer floor;
    boolean forDisabled;
}
