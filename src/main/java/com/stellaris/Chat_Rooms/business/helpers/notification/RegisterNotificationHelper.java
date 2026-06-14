package com.stellaris.Chat_Rooms.business.helpers.notification;

import com.stellaris.Chat_Rooms.persistence.entities.NotificationEntity;
import com.stellaris.Chat_Rooms.persistence.entities.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class RegisterNotificationHelper {
    private final NotificationRepository notificationRepository;

    public NotificationEntity register(RoomEntity room, String message) {
        NotificationEntity notification = NotificationEntity.builder()
                .room(room)
                .message(message)
                .build();
        return notificationRepository.save(notification);
    }
}
