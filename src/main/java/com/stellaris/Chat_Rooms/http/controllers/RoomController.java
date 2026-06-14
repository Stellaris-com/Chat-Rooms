package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.room.*;
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
    private final FetchRoomService fetchRoomService;
    private final EnterInRoomService enterInRoomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody @Valid CreateRoomRequestDTO request
    ) {
        return ResponseEntity.ok(createRoomService.create(currentUser, request));
    }

    @PostMapping("/{roomId}/enter")
    ResponseEntity<RoomResponseDTO> enterInRoom(
            @PathVariable UUID roomId,
            @AuthenticationPrincipal UserEntity currentUser
    ) {
        return ResponseEntity.ok(enterInRoomService.enter(currentUser, roomId));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> listRooms() {
        return ResponseEntity.ok(listRoomService.list());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDTO> fetchRoom(@PathVariable UUID roomId) {
        return ResponseEntity.ok(fetchRoomService.fetch(roomId));
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
