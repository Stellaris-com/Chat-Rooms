package com.stellaris.Chat_Rooms.persistence.mappers;

import com.stellaris.Chat_Rooms.http.dto.request.user.CreateUserRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.user.UserResponse;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity map(CreateUserRequestDTO request);
    UserResponse map(UserEntity currentUser);
}
