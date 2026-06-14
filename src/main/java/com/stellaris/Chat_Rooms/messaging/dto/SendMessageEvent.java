package com.stellaris.Chat_Rooms.messaging.dto;

import java.util.UUID;

public record SendMessageEvent(
        UUID userId,
        UUID roomId
) {
}
