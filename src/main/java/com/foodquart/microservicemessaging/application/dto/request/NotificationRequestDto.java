package com.foodquart.microservicemessaging.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
public class NotificationRequestDto {

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+[1-9]\\d{1,3}\\d{7,14}$", message = "Phone number must include country code (e.g., +57xxxxxxxxxx)")
    private String phoneNumber;

    @NotBlank(message = "Security pin is required")
    private String securityPin;

    @NotBlank(message = "Order id is required")
    private Long orderId;
}
