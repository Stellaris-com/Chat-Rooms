package com.stellaris.Chat_Rooms.http.dto.response.room;

import com.stellaris.Chat_Rooms.domain.enums.TypeOfMember;

import java.util.UUID;

public record RoomUserResponseDTO(
        UUID id,
        String username,
        TypeOfMember typeOfMember
) {
}
