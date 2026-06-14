package com.stellaris.Chat_Rooms.business.helpers.notification;

import com.stellaris.Chat_Rooms.persistence.entities.NotificationEntity;
import com.stellaris.Chat_Rooms.persistence.entities.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterNotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationEntity register(UserEntity currentUser, String message) {
        NotificationEntity notification = NotificationEntity.builder()
                .user(currentUser)
                .message(message)
                .build();
        return notificationRepository.save(notification);
    }
}
