package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.message.ListMessagesOfRoom;
import com.stellaris.Chat_Rooms.business.message.SendMessageService;
import com.stellaris.Chat_Rooms.http.dto.request.message.SendMessageRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.message.MessageResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final SendMessageService sendMessageService;
    private final ListMessagesOfRoom listMessagesOfRoom;

    @PostMapping("/{roomId}")
    public ResponseEntity<MessageResponseDTO> sendMessage(
            @AuthenticationPrincipal UserEntity currentUser,
            @PathVariable UUID roomId,
            @RequestBody @Valid SendMessageRequestDTO request
    ) {
        return ResponseEntity.ok(sendMessageService.send(currentUser, roomId, request));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<MessageResponseDTO>> listMessages(
            @PathVariable UUID roomId,
            @AuthenticationPrincipal UserEntity currentUser
    ) {
        return ResponseEntity.ok(listMessagesOfRoom.list(currentUser, roomId));
    }
}
