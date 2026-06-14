package com.stellaris.Chat_Rooms.business.helpers.user;

import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class IncreaseUserRoomsCreatedHelper {
    private final UserRepository userRepository;

    public void increaseRoomsCreated(UserEntity currentUser) {
        currentUser.increaseRoomsCreated();
        userRepository.save(currentUser);
    }
}
