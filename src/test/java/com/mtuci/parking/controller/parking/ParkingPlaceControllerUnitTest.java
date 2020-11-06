package com.mtuci.parking.controller.parking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtuci.parking.controller.account.AccountController;
import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.model.dto.parking.ParkingPlaceSearchParams;
import com.mtuci.parking.service.parking.ParkingPlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(ParkingPlaceController.class)
public class ParkingPlaceControllerUnitTest {

    @MockBean
    private ParkingPlaceService service;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private ParkingPlaceDto parkingPlaceDto;

    @BeforeEach
    public void init() {
        parkingPlaceDto = new ParkingPlaceDto(1L, "1", "1", 1, true, null);
        Mockito.when(service.findAll(Mockito.any(Pageable.class))).thenReturn(null);
        Mockito.when(service.findById(1L)).thenReturn(null);
        Mockito.doNothing().when(service).save(Mockito.any(ParkingPlaceDto.class));
        Mockito.doNothing().when(service).change(Mockito.anyLong(), Mockito.any(ParkingPlaceDto.class));
        Mockito.doNothing().when(service).deleteById(Mockito.anyLong());
        Mockito.when(service.search(Mockito.any(ParkingPlaceSearchParams.class), Mockito.any(Pageable.class))).thenReturn(null);
    }

    @Test
    public void findAll_returnOk() throws Exception {
        this.mockMvc.perform(get("/parking-places"))
                .andExpect(status().isOk());
    }

    @Test
    public void findById_returnOk() throws Exception {
        this.mockMvc.perform(get("/parking-places/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void save_returnCreated() throws Exception {
        this.mockMvc.perform(post("/parking-places")
                .content(objectMapper.writeValueAsString(parkingPlaceDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void change_returnOk() throws Exception {
        this.mockMvc.perform(patch("/parking-places/1")
                .content(objectMapper.writeValueAsString(parkingPlaceDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_returnNoContent() throws Exception {
        this.mockMvc.perform(delete("/parking-places/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void search_returnOk() throws Exception {
        this.mockMvc.perform(post("/parking-places/search")
                .content(objectMapper.writeValueAsString(new ParkingPlaceSearchParams()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
