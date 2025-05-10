package com.foodquart.microservicemessaging.infrastructure.input.rest;

import com.foodquart.microservicemessaging.application.dto.request.NotificationRequestDto;
import com.foodquart.microservicemessaging.application.dto.response.NotificationResponseDto;
import com.foodquart.microservicemessaging.application.handler.INotificationHandler;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationRestController {

    private final INotificationHandler notificationHandler;

    @PostMapping("/sms")
    public ResponseEntity<NotificationResponseDto> sendSmsNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        return ResponseEntity.ok(notificationHandler.sendSmsNotification(notificationRequestDto));
    }
}
