package com.mtuci.parking.repository.parking.specs;

import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ParkingPlaceSpecsUnitTest {

    @Test
    public void test_createSpecification_allNull() {
        ParkingPlaceSearchParams params = new ParkingPlaceSearchParams();
        Assertions.assertNotNull(ParkingPlaceSpecs.createSpecification(params));
    }

};
