package com.stellaris.Chat_Rooms.http.dto.response.message;

import java.util.UUID;

public record MessageUserResponseDTO(
        UUID id,
        String username
) {
}
