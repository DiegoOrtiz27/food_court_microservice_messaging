package com.foodquart.microservicemessaging.domain.api;

import com.foodquart.microservicemessaging.domain.model.NotificationModel;

public interface INotificationServicePort {
    boolean sendNotification(NotificationModel notificationModel);
}
