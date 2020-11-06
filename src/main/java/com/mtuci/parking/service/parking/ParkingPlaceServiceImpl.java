package com.mtuci.parking.service.parking;

import com.mtuci.parking.exception.ParkingPlaceNotFoundException;
import com.mtuci.parking.exception.ParkingPlaceNotFreeException;
import com.mtuci.parking.repository.parking.ParkingPlaceRepository;
import com.mtuci.parking.repository.parking.ReservationRepository;
import com.mtuci.parking.repository.parking.specs.ParkingPlaceSpecs;
import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import com.mtuci.parking.model.mapper.ParkingPlaceMapper;
import com.mtuci.parking.model.entity.parking.ParkingPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParkingPlaceServiceImpl implements ParkingPlaceService {

    private final ParkingPlaceRepository parkingPlaceRepository;
    private final ParkingPlaceMapper parkingPlaceMapper;
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public Page<ParkingPlaceDto> findAll(Pageable pageable) {
        return parkingPlaceRepository.findAll(pageable).map(parkingPlaceMapper::convertParkingPlaceToDto);
    }

    @Transactional(readOnly = true)
    public ParkingPlaceDto findById(Long id) {
        return parkingPlaceRepository.findById(id).map(parkingPlaceMapper::convertParkingPlaceToDto)
                .orElseThrow(() -> new ParkingPlaceNotFoundException("Parking place: " + id));
    }

    @Transactional
    public void save(ParkingPlaceDto place) {
        ParkingPlace pplace = ParkingPlace.builder()
                .id(null)
                .address(place.getAddress())
                .floor(place.getFloor())
                .zone(place.getZone())
                .forDisabled(place.isForDisabled())
                .build();
        parkingPlaceRepository.save(pplace);
    }

    @Transactional
    public void change(Long id, ParkingPlaceDto place) {
        ParkingPlace sourcePlace = parkingPlaceRepository.findById(id)
                .orElseThrow(() -> new ParkingPlaceNotFoundException("Parking place: " + id));
        if (place.getAddress() != null)
            sourcePlace.setAddress(place.getAddress());
        if (place.getZone() != null)
            sourcePlace.setZone(place.getZone());
        if (place.getFloor() != null)
            sourcePlace.setFloor(place.getFloor());
        sourcePlace.setForDisabled(place.isForDisabled());
        parkingPlaceRepository.save(sourcePlace);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!parkingPlaceRepository.existsById(id))
            throw new ParkingPlaceNotFoundException("Parking place: " + id);
        if (reservationRepository.existsByParkingPlaceId(id))
            throw new ParkingPlaceNotFreeException("This parking place is reserved");
        parkingPlaceRepository.deleteById(id);
    }

    @Transactional
    public Page<ParkingPlaceDto> search(ParkingPlaceSearchParams params, Pageable pageable) {
        Specification<ParkingPlace> spec = ParkingPlaceSpecs.createSpecification(params);
        return parkingPlaceRepository.findAll(spec, pageable).map(parkingPlaceMapper::convertParkingPlaceToDto);
    }

}
