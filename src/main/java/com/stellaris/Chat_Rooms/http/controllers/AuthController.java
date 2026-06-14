package com.stellaris.Chat_Rooms.http.controllers;

import com.stellaris.Chat_Rooms.business.user.CreateNewUserService;
import com.stellaris.Chat_Rooms.business.user.LoginUserService;
import com.stellaris.Chat_Rooms.business.user.LogoutUserService;
import com.stellaris.Chat_Rooms.http.dto.request.user.CreateUserRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.request.user.LoginUserRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.user.UserTokenResponse;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CreateNewUserService createNewUserService;
    private final LoginUserService loginUserService;
    private final LogoutUserService logoutUserService;

    @PostMapping("/register")
    public ResponseEntity<UserTokenResponse> register(@RequestBody @Valid CreateUserRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createNewUserService.createNewUser(request));
    }

    @GetMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody @Valid LoginUserRequestDTO request) {
        return ResponseEntity.ok(loginUserService.login(request));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserEntity currentUser) {
        logoutUserService.logout(currentUser);
        return ResponseEntity.ok().build();
    }
}
