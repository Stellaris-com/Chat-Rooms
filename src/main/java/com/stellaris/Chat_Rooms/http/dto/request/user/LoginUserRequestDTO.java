package com.stellaris.Chat_Rooms.http.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequestDTO(
        @NotBlank(message = "username é obrigatório")
        String username,

        @NotBlank(message = "password é obrigatório")
        String password
) {
}
