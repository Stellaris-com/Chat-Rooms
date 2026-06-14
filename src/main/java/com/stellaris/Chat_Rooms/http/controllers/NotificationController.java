package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.notification.ListNotificationsService;
import com.stellaris.Chat_Rooms.http.dto.response.notification.NotificationResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final ListNotificationsService listNotificationsService;

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> listNotification(
            @AuthenticationPrincipal UserEntity currentUser
    ) {
        return ResponseEntity.ok(listNotificationsService.list(currentUser));
    }
}
