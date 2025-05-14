package com.foodquart.microservicemessaging.domain.util;

public class NotificationMessages {
    public static final String SMS_SENT_SUCCESS = "The message has been sent successfully";
    public static final String SMS_SENT_ERROR = "The message could not be sent";
    public static final String BAD_NUMBER = "Phone number must include country code (e.g., +57xxxxxxxxxx)";
    public static final String NUMBER_REQUIRED = "The number is required";
    public static final String MESSAGE_REQUIRED = "The message is required";

    private NotificationMessages() {
        throw new IllegalStateException("Utility class");
    }
}
