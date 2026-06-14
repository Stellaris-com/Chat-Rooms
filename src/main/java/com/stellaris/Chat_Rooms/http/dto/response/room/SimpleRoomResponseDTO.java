package com.stellaris.Chat_Rooms.http.dto.response.room;

import java.util.UUID;

public record SimpleRoomResponseDTO(
        UUID id,
        String name
) {
}
