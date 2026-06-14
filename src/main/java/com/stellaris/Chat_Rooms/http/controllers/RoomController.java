package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.room.CreateRoomService;
import com.stellaris.Chat_Rooms.http.dto.response.room.CreateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final CreateRoomService createRoomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody @Valid CreateRoomRequestDTO request
    ) {
        return ResponseEntity.ok(createRoomService.create(currentUser, request));
    }
}
