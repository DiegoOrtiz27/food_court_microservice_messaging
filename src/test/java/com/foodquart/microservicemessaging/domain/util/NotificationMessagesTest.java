package com.foodquart.microservicemessaging.domain.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMessagesTest {

    @Test
    void constructorShouldThrowIllegalStateException() {
        assertThrows(IllegalStateException.class, NotificationMessages::new, "Utility class");
    }
}