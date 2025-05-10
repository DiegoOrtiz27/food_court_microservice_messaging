package com.foodquart.microservicemessaging.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {
    private String phoneNumber;
    private String message;
    private String securityPin;
    private Long orderId;
}
