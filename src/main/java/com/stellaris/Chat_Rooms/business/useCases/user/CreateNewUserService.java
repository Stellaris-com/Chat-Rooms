package com.stellaris.Chat_Rooms.business.useCases.user;

import com.stellaris.Chat_Rooms.http.dto.request.user.CreateUserRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.user.UserTokenResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.UserMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.UserRepository;
import com.stellaris.Chat_Rooms.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNewUserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserTokenResponseDTO createNewUser(CreateUserRequestDTO request) {
        UserEntity userToRegister = userMapper.map(request);
        userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));

        UserEntity userRegistered = userRepository.save(userToRegister);
        return new UserTokenResponseDTO(tokenService.generateAccessToken(userRegistered));
    }
}
