package com.stellaris.Chat_Rooms.business.helpers.user;

import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncreaseUserMessagesService {
    private final UserRepository userRepository;

    public void increaseMessage(UserEntity currentUser) {
        currentUser.increaseMessagingSend();
        userRepository.save(currentUser);
    }
}
