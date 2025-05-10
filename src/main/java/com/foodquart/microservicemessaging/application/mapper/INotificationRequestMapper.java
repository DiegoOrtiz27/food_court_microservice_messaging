package com.foodquart.microservicemessaging.application.mapper;

import com.foodquart.microservicemessaging.application.dto.request.NotificationRequestDto;
import com.foodquart.microservicemessaging.domain.model.NotificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface INotificationRequestMapper {
    NotificationModel toModel(NotificationRequestDto notificationRequestDto);
}
