package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.useCases.notification.ListNotificationsService;
import com.stellaris.Chat_Rooms.http.dto.response.notification.NotificationResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final ListNotificationsService listNotificationsService;

    @GetMapping("/{roomId}")
    public ResponseEntity<List<NotificationResponseDTO>> listNotification(
            @AuthenticationPrincipal UserEntity currentUser,
            @PathVariable UUID roomId
    ) {
        return ResponseEntity.ok(listNotificationsService.list(currentUser, roomId));
    }
}
