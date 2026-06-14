package com.stellaris.Chat_Rooms.business.user;

import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogoutUserService {
    private final TokenRepository tokenRepository;

    @Transactional
    public void logout(UserEntity currentUser) {
        tokenRepository.deleteByUserId(currentUser.getId());
    }
}
