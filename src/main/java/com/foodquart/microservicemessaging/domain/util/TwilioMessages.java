package com.foodquart.microservicemessaging.domain.util;

public final class TwilioMessages {
    public static final String SMS_SENT_SUCCESS = "SMS successfully sent to %s - SID: %s";

    public static final String API_ERROR = "Twilio API error when sending SMS: %s - Code: %s";
    public static final String AUTHENTICATION_ERROR = "Authentication error with Twilio: %s";
    public static final String GENERAL_ERROR = "Twilio General Error: %s";
    public static final String UNEXPECTED_ERROR = "Unexpected error while sending SMS: %s";

    private TwilioMessages() {
        throw new IllegalStateException("Utility class");
    }
}