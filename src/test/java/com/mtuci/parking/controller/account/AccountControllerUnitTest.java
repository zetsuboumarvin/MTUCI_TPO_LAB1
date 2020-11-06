package com.mtuci.parking.controller.account;

import com.mtuci.parking.model.dto.account.UserDto;
import com.mtuci.parking.service.account.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(AccountController.class)
public class AccountControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountServiceImpl service;

    @BeforeEach
    public void init() {
        Mockito.when(service.getAccountInfo())
                .thenReturn(new UserDto(1L, "1", "Admin", null));
    }

    @Test
    public void getAccountInfo_shouldReturnUserDto() throws Exception {
        this.mockMvc.perform(get("/me"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.authority").value("Admin"));
    }
}
