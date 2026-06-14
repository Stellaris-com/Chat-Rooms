package com.stellaris.Chat_Rooms.websocket.dto.send;

import java.time.LocalDateTime;
import java.util.UUID;

public record SendMessageWebSocketSend(
        UUID id,
        String message,
        MessageWebSocketUserSend user,
        LocalDateTime createdAt
) {
}
