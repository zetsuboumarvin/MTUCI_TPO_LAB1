package com.mtuci.parking.controller.parking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtuci.parking.model.dto.parking.ParkingPlaceDto;
import com.mtuci.parking.service.parking.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(ReservationController.class)
public class ReservationControllerUnitTest {

    @MockBean
    private ReservationService service;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        Mockito.when(service.findAll(Mockito.any(Pageable.class))).thenReturn(null);
        Mockito.when(service.findById(1L)).thenReturn(null);
        Mockito.doNothing().when(service).save(Mockito.anyLong());
        Mockito.doNothing().when(service).deleteById(Mockito.anyLong());
    }

    @Test
    public void findAll_returnOk() throws Exception {
        this.mockMvc.perform(get("/reservation"))
                .andExpect(status().isOk());
    }

    @Test
    public void findById_returnOk() throws Exception {
        this.mockMvc.perform(get("/reservation/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void save_returnOk() throws Exception {
        this.mockMvc.perform(put("/reservation/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_returnOk() throws Exception {
        this.mockMvc.perform(delete("/reservation/1"))
                .andExpect(status().isOk());
    }
}
