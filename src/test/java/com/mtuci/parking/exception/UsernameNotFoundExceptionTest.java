package com.mtuci.parking.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UsernameNotFoundExceptionTest {

    @Test
    public void usernameNotFoundExceptionTest() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {throw new UsernameNotFoundException("test");});
    }
}
