package com.stellaris.Chat_Rooms.messaging.dto;

import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;

import java.util.UUID;

public record SendMessageEvent(
        UUID userId,
        UUID roomId,
        UUID messageId
) {
}
