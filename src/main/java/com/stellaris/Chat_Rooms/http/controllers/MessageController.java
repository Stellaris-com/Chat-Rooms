package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.message.SendMessageService;
import com.stellaris.Chat_Rooms.http.dto.request.message.SendMessageRequestDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final SendMessageService sendMessageService;

    @PostMapping("/{roomId}")
    public ResponseEntity<Void> sendMessage(
            @AuthenticationPrincipal UserEntity currentUser,
            @PathVariable UUID roomId,
            @RequestBody @Valid SendMessageRequestDTO request
    ) {
        sendMessageService.send(currentUser, roomId, request);
        return ResponseEntity.ok().build();
    }
}
