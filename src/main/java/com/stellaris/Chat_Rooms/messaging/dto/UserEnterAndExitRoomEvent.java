package com.stellaris.Chat_Rooms.messaging.dto;

import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;

public record UserEnterAndExitRoomEvent(
        UserEntity user,
        RoomEntity room
) {
}
