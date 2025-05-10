package com.foodquart.microservicemessaging.application.handler;

import com.foodquart.microservicemessaging.application.dto.request.NotificationRequestDto;
import com.foodquart.microservicemessaging.application.dto.response.NotificationResponseDto;

public interface INotificationHandler {
    NotificationResponseDto sendSmsNotification(NotificationRequestDto notificationRequestDto);
}
