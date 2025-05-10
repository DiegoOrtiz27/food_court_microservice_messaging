package com.foodquart.microservicemessaging.application.mapper;

import com.foodquart.microservicemessaging.application.dto.response.NotificationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface INotificationResponseMapper {
    NotificationResponseDto toResponse(boolean success, String response);
}
