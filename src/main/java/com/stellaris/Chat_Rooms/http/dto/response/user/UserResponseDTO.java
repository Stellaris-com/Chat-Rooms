package com.stellaris.Chat_Rooms.http.dto.response.user;

import com.stellaris.Chat_Rooms.domain.enums.Role;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        Long messages,
        Long roomsCreated,
        Role role
) {
}
