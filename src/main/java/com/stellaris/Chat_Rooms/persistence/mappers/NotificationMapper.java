package com.stellaris.Chat_Rooms.persistence.mappers;

import com.stellaris.Chat_Rooms.http.dto.response.notification.NotificationResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.NotificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponseDTO map(NotificationEntity entity);
}
