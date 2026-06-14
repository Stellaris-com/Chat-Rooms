package com.stellaris.Chat_Rooms.http.dto.request.room;

import jakarta.validation.constraints.NotBlank;

public record CreateRoomRequestDTO(
        @NotBlank(message = "name é obrigatório")
        String name
) {
}
