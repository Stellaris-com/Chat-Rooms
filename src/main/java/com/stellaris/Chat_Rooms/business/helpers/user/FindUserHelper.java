package com.stellaris.Chat_Rooms.business.helpers.user;

import com.stellaris.Chat_Rooms.http.exceptions.UserNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindUserHelper {
    private final UserRepository userRepository;

    public UserEntity findUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
