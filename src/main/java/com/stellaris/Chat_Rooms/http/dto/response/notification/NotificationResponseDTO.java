package com.stellaris.Chat_Rooms.http.dto.response.notification;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponseDTO(
        UUID id,
        String message,
        LocalDateTime createdAt
) {
}
