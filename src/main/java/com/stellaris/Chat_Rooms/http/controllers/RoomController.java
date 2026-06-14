package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.room.CreateRoomService;
import com.stellaris.Chat_Rooms.business.room.ListRoomService;
import com.stellaris.Chat_Rooms.business.room.UpdateRoomService;
import com.stellaris.Chat_Rooms.http.dto.request.room.UpdateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.request.room.CreateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final CreateRoomService createRoomService;
    private final ListRoomService listRoomService;
    private final UpdateRoomService updateRoomService;

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

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody @Valid UpdateRoomRequestDTO request,
            @PathVariable UUID roomId
            ) {
        return ResponseEntity.ok(updateRoomService.update(currentUser, request, roomId));
    }
}
