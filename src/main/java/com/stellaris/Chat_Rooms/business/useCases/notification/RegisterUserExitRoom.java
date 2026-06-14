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
public class RegisterUserExitRoom {
    private final NotificationRepository notificationRepository;
    private final FindRoomByUserHelper findRoomByUserHelper;
    private final RegisterNotificationHelper registerNotificationHelper;

    public void userExitRoom(UserEntity currentUser, UUID roomId) {
        RoomEntity roomFound = findRoomByUserHelper.findByUser(currentUser, roomId);
        registerNotificationHelper.register(roomFound, currentUser.getUsername() + "saiu da sala " + roomFound.getName());
    }
}
