package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.room.CreateRoomService;
import com.stellaris.Chat_Rooms.business.room.ListRoomService;
import com.stellaris.Chat_Rooms.http.dto.response.room.CreateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final CreateRoomService createRoomService;
    private final ListRoomService listRoomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody @Valid CreateRoomRequestDTO request
    ) {
        return ResponseEntity.ok(createRoomService.create(currentUser, request));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> listRooms() {
        return ResponseEntity.ok(listRoomService.list());
    }
}
