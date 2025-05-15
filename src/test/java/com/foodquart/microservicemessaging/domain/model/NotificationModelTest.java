package com.foodquart.microservicemessaging.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationModelTest {

    @Test
    void shouldCreateNotificationModelWithAllArgsConstructor() {
        String phoneNumber = "+573001234567";
        String message = "Test notification message";

        NotificationModel notification = new NotificationModel(phoneNumber, message);

        assertNotNull(notification);
        assertEquals(phoneNumber, notification.getPhoneNumber());
        assertEquals(message, notification.getMessage());
    }

    @Test
    void shouldCreateNotificationModelWithNoArgsConstructor() {
        NotificationModel notification = new NotificationModel();
        assertNotNull(notification);
    }

    @Test
    void shouldSetAndGetPhoneNumber() {
        NotificationModel notification = new NotificationModel();
        String phoneNumber = "+15559876543";
        notification.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, notification.getPhoneNumber());
    }

    @Test
    void shouldSetAndGetMessage() {
        NotificationModel notification = new NotificationModel();
        String message = "Another test message";
        notification.setMessage(message);
        assertEquals(message, notification.getMessage());
    }
}