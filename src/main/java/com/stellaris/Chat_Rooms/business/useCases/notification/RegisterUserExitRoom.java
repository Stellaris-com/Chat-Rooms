package com.stellaris.Chat_Rooms.business.useCases.notification;

import com.stellaris.Chat_Rooms.business.helpers.notification.RegisterNotificationHelper;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserExitRoom {
    private final RegisterNotificationHelper registerNotificationHelper;

    public void userExitRoom(UserEntity currentUser, RoomEntity room) {
        registerNotificationHelper.register(room, currentUser.getUsername() + "saiu da sala " + room.getName());
    }
}
