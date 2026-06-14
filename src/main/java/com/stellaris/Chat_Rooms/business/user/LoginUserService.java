package com.stellaris.Chat_Rooms.business.user;

import com.stellaris.Chat_Rooms.http.dto.request.user.LoginUserRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.user.UserTokenResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public UserTokenResponseDTO login(LoginUserRequestDTO request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        UserEntity userEntity = (UserEntity) authenticationManager.authenticate(authenticationToken).getPrincipal();

        String accessToken = tokenService.generateAccessToken(userEntity);
        return new UserTokenResponseDTO(accessToken);
    }
}
