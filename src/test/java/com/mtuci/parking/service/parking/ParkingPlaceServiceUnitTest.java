package com.mtuci.parking.service.parking;

import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import com.mtuci.parking.model.entity.parking.ParkingPlace;
import com.mtuci.parking.model.mapper.ParkingPlaceMapper;
import com.mtuci.parking.exception.ParkingPlaceNotFoundException;
import com.mtuci.parking.exception.ParkingPlaceNotFreeException;
import com.mtuci.parking.repository.parking.ParkingPlaceRepository;
import com.mtuci.parking.repository.parking.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ParkingPlaceServiceUnitTest {

    @Mock
    private ParkingPlaceRepository parkingPlaceRepository;
    @Mock
    private ParkingPlaceMapper parkingPlaceMapper;
    @Mock
    private ReservationRepository reservationRepository;
    private ParkingPlaceService service;

    @BeforeEach
    public void init() {
        this.service = new ParkingPlaceServiceImpl(parkingPlaceRepository, parkingPlaceMapper, reservationRepository);
        ParkingPlace place = new ParkingPlace();
        ParkingPlaceDto dto = new ParkingPlaceDto(1L, "1", "1", 1, false, null);
        Page<ParkingPlace> result = new PageImpl<>(List.of(place));
        Mockito.when(parkingPlaceMapper.convertParkingPlaceToDto(Mockito.any(ParkingPlace.class))).thenReturn(dto);
        Mockito.when(parkingPlaceRepository.findAll(Mockito.any(Pageable.class))).thenReturn(result);
        Mockito.when(parkingPlaceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(place));
        Mockito.when(parkingPlaceRepository.findById(-1L)).thenReturn(Optional.empty());
        Mockito.when(parkingPlaceRepository.save(Mockito.any(ParkingPlace.class))).thenReturn(place);
        Mockito.when(parkingPlaceRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(parkingPlaceRepository.existsById(-1L)).thenReturn(false);
        Mockito.when(reservationRepository.existsByParkingPlaceId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(reservationRepository.existsByParkingPlaceId(-2L)).thenReturn(true);
        Mockito.when(parkingPlaceRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(result);
    }

    @Test
    public void findAll_getPageable() {
        assert service.findAll(PageRequest.of(1, 1)) != null;
    }

    @Test
    public void findById_getParkingPlaceDto() {
        assert service.findById(Mockito.anyLong()) != null;
    }

    @Test
    public void findById_whenEmptyResponse_getParkingPlaceNotFoundException() {
        Exception ex = Assertions.assertThrows(ParkingPlaceNotFoundException.class, () -> service.findById(-1L));
        assert ex.getMessage().contains("Parking place: ");
    }

    @Test
    public void save_getNoException() {
        service.save(new ParkingPlaceDto(null, "1", "1", 1, true, null));
    }

    @Test
    public void change_getNoException() {
        service.change(Mockito.anyLong(), new ParkingPlaceDto(null, "1", "1", 1, true, null));
    }

    @Test
    public void deleteByid_getNoException() {
        service.deleteById(Mockito.anyLong());
    }

    @Test
    public void deleteById_whenPPNotExist_getParkingPlaceNotFoundException() {
        Exception ex = Assertions.assertThrows(ParkingPlaceNotFoundException.class, () -> service.deleteById(-1L));
        assert ex.getMessage().contains("Parking place: ");
    }

    @Test
    public void deleteById_whenReservExist_getParkingPlaceNotFreeException() {
        Exception ex = Assertions.assertThrows(ParkingPlaceNotFreeException.class, () -> service.deleteById(-2L));
        assert ex.getMessage().contains("This parking place is reserved");
    }

    @Test
    public void search() {
        assert service.search(new ParkingPlaceSearchParams(), PageRequest.of(1, 1)) != null;
    }
}
