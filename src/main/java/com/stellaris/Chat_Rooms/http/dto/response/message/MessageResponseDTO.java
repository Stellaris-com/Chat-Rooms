package com.stellaris.Chat_Rooms.http.dto.response.message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageResponseDTO(
        UUID id,
        String message,
        MessageUserResponseDTO user,
        LocalDateTime createdAt
) {
}
