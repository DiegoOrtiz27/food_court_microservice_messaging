package com.foodquart.microservicemessaging.application.handler.impl;

import com.foodquart.microservicemessaging.application.dto.request.NotificationRequestDto;
import com.foodquart.microservicemessaging.application.dto.response.NotificationResponseDto;
import com.foodquart.microservicemessaging.application.handler.INotificationHandler;
import com.foodquart.microservicemessaging.application.mapper.INotificationRequestMapper;
import com.foodquart.microservicemessaging.application.mapper.INotificationResponseMapper;
import com.foodquart.microservicemessaging.domain.api.INotificationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.foodquart.microservicemessaging.domain.util.NotificationMessages.SMS_SENT_SUCCESS;

@Service
@RequiredArgsConstructor
public class NotificationHandler implements INotificationHandler {

    private final INotificationServicePort notificationServicePort;
    private final INotificationRequestMapper notificationMapper;
    private final INotificationResponseMapper notificationResponseMapper;

    @Override
    public NotificationResponseDto sendSmsNotification(NotificationRequestDto notificationRequestDto) {
        boolean success = notificationServicePort.sendNotification(notificationMapper.toModel(notificationRequestDto));
        return notificationResponseMapper.toResponse(success, SMS_SENT_SUCCESS);
    }
}
