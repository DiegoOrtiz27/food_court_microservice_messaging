package com.foodquart.microservicemessaging.domain.usecase;

import com.foodquart.microservicemessaging.domain.exception.DomainException;
import com.foodquart.microservicemessaging.domain.model.NotificationModel;
import com.foodquart.microservicemessaging.domain.spi.IMessagingProviderPort;
import com.foodquart.microservicemessaging.domain.util.NotificationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationUseCaseTest {

    @Mock
    private IMessagingProviderPort messagingProviderPort;

    private NotificationUseCase notificationUseCase;
    private final String validPhoneNumber = "+573001234567";

    @BeforeEach
    void setUp() {
        notificationUseCase = new NotificationUseCase(messagingProviderPort);
    }

    private NotificationModel createTestNotification(String phoneNumber) {
        NotificationModel notification = new NotificationModel();
        notification.setPhoneNumber(phoneNumber);
        notification.setMessage("Test message");
        notification.setOrderId(123L);
        return notification;
    }

    @Test
    @DisplayName("Should send notification successfully with valid phone number")
    void shouldSendNotificationSuccessfully() {
        NotificationModel notification = createTestNotification(validPhoneNumber);

        when(messagingProviderPort.sendSmsNotification(notification)).thenReturn(true);

        boolean result = notificationUseCase.sendNotification(notification);

        assertTrue(result);
        verify(messagingProviderPort).sendSmsNotification(notification);
    }

    @Test
    @DisplayName("Should throw exception when phone number is null")
    void shouldThrowWhenPhoneNumberIsNull() {
        NotificationModel notification = createTestNotification(null);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(NotificationMessages.BAD_NUMBER, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when phone number is invalid")
    void shouldThrowWhenPhoneNumberIsInvalid() {
        String invalidPhoneNumber = "3001234567";
        NotificationModel notification = createTestNotification(invalidPhoneNumber);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(NotificationMessages.BAD_NUMBER, exception.getMessage());
        verify(messagingProviderPort, never()).sendSmsNotification(any());
    }

    @Test
    @DisplayName("Should throw exception when SMS provider fails to send")
    void shouldThrowWhenSmsProviderFails() {
        NotificationModel notification = createTestNotification(validPhoneNumber);

        when(messagingProviderPort.sendSmsNotification(notification)).thenReturn(false);

        DomainException exception = assertThrows(DomainException.class,
                () -> notificationUseCase.sendNotification(notification));

        assertEquals(NotificationMessages.SMS_SENT_ERROR, exception.getMessage());
        verify(messagingProviderPort).sendSmsNotification(notification);
    }

    @Test
    @DisplayName("isValidPhoneNumber should return true for valid number")
    void isValidPhoneNumberShouldReturnTrueForValidNumber() {
        assertTrue(notificationUseCase.isValidPhoneNumber("+573001234567"));
    }

    @Test
    @DisplayName("isValidPhoneNumber should return false for null number")
    void isValidPhoneNumberShouldReturnFalseForNullNumber() {
        assertFalse(notificationUseCase.isValidPhoneNumber(null));
    }

    @Test
    @DisplayName("isValidPhoneNumber should return false for invalid number")
    void isValidPhoneNumberShouldReturnFalseForInvalidNumber() {
        assertFalse(notificationUseCase.isValidPhoneNumber("573001234567"));
    }
}