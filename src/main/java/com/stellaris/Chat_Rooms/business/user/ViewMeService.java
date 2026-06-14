package com.stellaris.Chat_Rooms.business.user;

import com.stellaris.Chat_Rooms.http.dto.response.user.UserResponse;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewMeService {
    private final UserMapper userMapper;

    public UserResponse viewMe(UserEntity currentUser) {
        return userMapper.map(currentUser);
    }
}
