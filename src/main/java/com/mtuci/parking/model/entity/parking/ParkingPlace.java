package com.mtuci.parking.model.entity.parking;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ppGenerator")
    @SequenceGenerator(name = "ppGenerator")
    Long id;

    String address;

    String zone;

    Integer floor;

    boolean forDisabled;
}
