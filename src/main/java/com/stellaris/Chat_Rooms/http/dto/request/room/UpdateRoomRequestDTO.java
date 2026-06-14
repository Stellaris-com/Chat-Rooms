package com.stellaris.Chat_Rooms.http.dto.request.room;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoomRequestDTO(
        @NotBlank(message = "name é obrigatório")
        String name
) {
}
