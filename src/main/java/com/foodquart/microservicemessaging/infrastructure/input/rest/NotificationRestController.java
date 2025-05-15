package com.foodquart.microservicemessaging.infrastructure.input.rest;

import com.foodquart.microservicemessaging.application.dto.request.NotificationRequestDto;
import com.foodquart.microservicemessaging.application.dto.response.NotificationResponseDto;
import com.foodquart.microservicemessaging.application.handler.INotificationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.foodquart.microservicemessaging.infrastructure.documentation.APINotificationDocumentationConstant.*;
import static com.foodquart.microservicemessaging.infrastructure.documentation.ResponseCode.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationRestController {

    private final INotificationHandler notificationHandler;

    @PostMapping("/sms")
    @Operation(summary = SEND_SMS_NOTIFICATION_SUMMARY)
    @ApiResponse(responseCode = CODE_200, description = SEND_SMS_NOTIFICATION_SUCCESS_DESCRIPTION,
            content = @Content(schema = @Schema(implementation = NotificationResponseDto.class)))
    public ResponseEntity<NotificationResponseDto> sendSmsNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        return ResponseEntity.ok(notificationHandler.sendSmsNotification(notificationRequestDto));
    }
}
