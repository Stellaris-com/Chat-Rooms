package com.stellaris.Chat_Rooms.messaging.dto;

import java.util.UUID;

public record UserEnterAndExitRoomEvent(
        UUID userId,
        UUID roomId
) {
}
