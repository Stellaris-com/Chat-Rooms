package com.stellaris.Chat_Rooms.persistence.mappers;

import com.stellaris.Chat_Rooms.http.dto.request.message.SendMessageRequestDTO;
import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageEntity map(SendMessageRequestDTO request);
}
