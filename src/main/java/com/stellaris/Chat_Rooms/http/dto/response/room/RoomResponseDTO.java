package com.stellaris.Chat_Rooms.http.dto.response.room;

import java.util.List;
import java.util.UUID;

public record RoomResponseDTO(
        UUID id,
        String name,
        List<RoomUserResponseDTO> membersOfRoom
) {
}
