package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.user.ViewMeService;
import com.stellaris.Chat_Rooms.http.dto.response.user.UserResponse;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final ViewMeService viewMeService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> viewMe(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(viewMeService.viewMe(currentUser));
    }
}
