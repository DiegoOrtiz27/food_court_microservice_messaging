package com.foodquart.microservicemessaging.domain.util;

public class NotificationMessages {
    public static final String SMS_SENT_SUCCESS = "The message has been sent successfully";
    public static final String SMS_SENT_ERROR = "The message could not be sent";
    public static final String BAD_NUMBER = "The message could not be sent";

    private NotificationMessages() {
        throw new IllegalStateException("Utility class");
    }
}
