package com.stellaris.Chat_Rooms.business.useCases.notification;

import com.stellaris.Chat_Rooms.business.helpers.notification.RegisterNotificationHelper;
import com.stellaris.Chat_Rooms.business.helpers.room.FindRoomByUserHelper;
import com.stellaris.Chat_Rooms.persistence.entities.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterUserEnterInRoom {
    private final NotificationRepository notificationRepository;
    private final FindRoomByUserHelper findRoomByUserHelper;
    private final RegisterNotificationHelper registerNotificationHelper;

    public void userEnterInRoom(UserEntity currentUser, RoomEntity room) {
        registerNotificationHelper.register(room, currentUser.getUsername() + "entrou na sala " + room.getName());
    }
}
