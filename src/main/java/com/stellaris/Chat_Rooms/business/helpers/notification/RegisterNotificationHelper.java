package com.stellaris.Chat_Rooms.business.helpers.notification;

import com.stellaris.Chat_Rooms.persistence.entities.NotificationEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
