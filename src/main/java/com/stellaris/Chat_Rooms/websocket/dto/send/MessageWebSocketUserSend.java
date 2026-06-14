package com.stellaris.Chat_Rooms.websocket.dto.send;

import java.util.UUID;

public record MessageWebSocketUserSend(
        UUID id,
        String username
) {
}
