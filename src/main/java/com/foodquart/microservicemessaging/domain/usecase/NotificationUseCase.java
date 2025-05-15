package com.foodquart.microservicemessaging.domain.usecase;

import com.foodquart.microservicemessaging.domain.api.INotificationServicePort;
import com.foodquart.microservicemessaging.domain.exception.DomainException;
import com.foodquart.microservicemessaging.domain.model.NotificationModel;
import com.foodquart.microservicemessaging.domain.spi.IMessagingProviderPort;

import static com.foodquart.microservicemessaging.domain.util.NotificationMessages.*;

public class NotificationUseCase implements INotificationServicePort {

    public static final String PHONE_REGEX = "^\\+[1-9]\\d{1,14}$";

    private final IMessagingProviderPort messagingProviderPort;

    public NotificationUseCase(IMessagingProviderPort messagingProviderPort) {
        this.messagingProviderPort = messagingProviderPort;
    }

    @Override
    public boolean sendNotification(NotificationModel notificationModel) {

        if (notificationModel.getPhoneNumber() == null || notificationModel.getPhoneNumber().isEmpty()) {
            throw new DomainException(NUMBER_REQUIRED);
        }

        if (!isValidPhoneNumber(notificationModel.getPhoneNumber())) {
            throw new DomainException(BAD_NUMBER);
        }

        if (notificationModel.getMessage() == null || notificationModel.getMessage().isEmpty()) {
            throw new DomainException(MESSAGE_REQUIRED);
        }

        if (!messagingProviderPort.sendSmsNotification(notificationModel)) {
            throw new DomainException(SMS_SENT_ERROR);
        }
        return true;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(PHONE_REGEX);
    }
}
