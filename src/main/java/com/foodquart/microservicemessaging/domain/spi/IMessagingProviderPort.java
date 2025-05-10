package com.foodquart.microservicemessaging.domain.spi;

import com.foodquart.microservicemessaging.domain.model.NotificationModel;

public interface IMessagingProviderPort {
    boolean sendSmsNotification(NotificationModel notificationModel);
}
