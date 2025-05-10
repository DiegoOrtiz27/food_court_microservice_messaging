package com.foodquart.microservicemessaging.domain.usecase;

import com.foodquart.microservicemessaging.domain.api.INotificationServicePort;
import com.foodquart.microservicemessaging.domain.exception.DomainException;
import com.foodquart.microservicemessaging.domain.model.NotificationModel;
import com.foodquart.microservicemessaging.domain.spi.IMessagingProviderPort;
import com.foodquart.microservicemessaging.domain.util.NotificationMessages;

public class NotificationUseCase implements INotificationServicePort {

    private final IMessagingProviderPort messagingProviderPort;

    public NotificationUseCase(IMessagingProviderPort messagingProviderPort) {
        this.messagingProviderPort = messagingProviderPort;
    }

    @Override
    public boolean sendNotification(NotificationModel notificationModel) {
        if (!isValidPhoneNumber(notificationModel.getPhoneNumber())) {
            throw new DomainException(NotificationMessages.BAD_NUMBER);
        }
        if (!messagingProviderPort.sendSmsNotification(notificationModel)) {
            throw new DomainException(NotificationMessages.SMS_SENT_ERROR);
        }
        return true;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+[1-9]\\d{1,14}$");
    }
}
