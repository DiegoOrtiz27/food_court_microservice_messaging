package com.foodquart.microservicemessaging.domain.usecase;

import com.foodquart.microservicemessaging.domain.exception.DomainException;
import com.foodquart.microservicemessaging.domain.model.NotificationModel;
import com.foodquart.microservicemessaging.domain.spi.IMessagingProviderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.foodquart.microservicemessaging.domain.util.NotificationMessages.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationUseCaseTest {

    @Mock
    private IMessagingProviderPort messagingProviderPort;

    private NotificationUseCase notificationUseCase;
    private final String validPhoneNumber = "+573244881219";
    private final String testMessage = "Test message";

    @BeforeEach
    void setUp() {
        notificationUseCase = new NotificationUseCase(messagingProviderPort);
    }

    private NotificationModel createTestNotification(String phoneNumber, String message) {
        NotificationModel notification = new NotificationModel();
        notification.setPhoneNumber(phoneNumber);
        notification.setMessage(message);
        return notification;
    }

    @Test
    @DisplayName("Should send notification successfully with valid phone number and message")
    void shouldSendNotificationSuccessfully() {
        NotificationModel notification = createTestNotification(validPhoneNumber, testMessage);

        when(messagingProviderPort.sendSmsNotification(notification)).thenReturn(true);

        boolean result = notificationUseCase.sendNotification(notification);

        assertTrue(result);
        verify(messagingProviderPort).sendSmsNotification(notification);
    }

    @Test
    @DisplayName("Should throw exception when phone number is null")
    void shouldThrowWhenPhoneNumberIsNull() {
        NotificationModel notification = createTestNotification(null, testMessage);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(NUMBER_REQUIRED, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when phone number is invalid")
    void shouldThrowWhenPhoneNumberIsInvalid() {
        String invalidPhoneNumber = "3001234567";
        NotificationModel notification = createTestNotification(invalidPhoneNumber, testMessage);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(BAD_NUMBER, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when phone number is empty")
    void shouldThrowWhenPhoneNumberIsEmpty() {
        NotificationModel notification = createTestNotification("", testMessage);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(NUMBER_REQUIRED, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when message is empty")
    void shouldThrowWhenMessageIsEmpty() {
        NotificationModel notification = createTestNotification(validPhoneNumber, "");

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(MESSAGE_REQUIRED, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when message is null")
    void shouldThrowWhenMessageIsNull() {
        NotificationModel notification = createTestNotification(validPhoneNumber, null);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(MESSAGE_REQUIRED, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when SMS provider fails to send")
    void shouldThrowWhenSmsProviderFails() {
        NotificationModel notification = createTestNotification(validPhoneNumber, testMessage);

        when(messagingProviderPort.sendSmsNotification(notification)).thenReturn(false);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(SMS_SENT_ERROR, exception.getMessage());
        verify(messagingProviderPort).sendSmsNotification(notification);
    }

    @Test
    @DisplayName("isValidPhoneNumber should return true for valid number")
    void isValidPhoneNumberShouldReturnTrueForValidNumber() {
        assertTrue(notificationUseCase.isValidPhoneNumber("+573001234567"));
        assertTrue(notificationUseCase.isValidPhoneNumber("+15551234567")); // Another valid format
    }

    @Test
    @DisplayName("isValidPhoneNumber should return false for null number")
    void isValidPhoneNumberShouldReturnFalseForNullNumber() {
        assertFalse(notificationUseCase.isValidPhoneNumber(null));
    }

    @Test
    @DisplayName("isValidPhoneNumber should return false for invalid number formats")
    void isValidPhoneNumberShouldReturnFalseForInvalidNumber() {
        assertFalse(notificationUseCase.isValidPhoneNumber("573001234567")); // Missing '+'
        assertFalse(notificationUseCase.isValidPhoneNumber("+573001234567890123456")); // Too long
        assertFalse(notificationUseCase.isValidPhoneNumber("+57abc1234567")); // Contains letters
        assertFalse(notificationUseCase.isValidPhoneNumber("")); // Empty string
    }
}