package com.mtuci.parking.model.entity.parking;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservGenerator")
    @SequenceGenerator(name = "reservGenerator")
    Long id;

    @OneToOne(targetEntity = ParkingPlace.class)
    ParkingPlace parkingPlace;

    Long userId;

    LocalDateTime startOfReservation;
}
