package com.foodquart.microservicemessaging.domain.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwilioMessagesTest {

    @Test
    void constructorShouldThrowIllegalStateException() {
        assertThrows(IllegalStateException.class, TwilioMessages::new, "Utility class");
    }
}