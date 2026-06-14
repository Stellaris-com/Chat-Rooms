package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.user.CreateNewUserService;
import com.stellaris.Chat_Rooms.http.dto.request.user.CreateUserRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.user.UserTokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CreateNewUserService createNewUserService;

    @PostMapping("/register")
    public ResponseEntity<UserTokenResponse> register(@RequestBody @Valid CreateUserRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createNewUserService.createNewUser(request));
    }
}
