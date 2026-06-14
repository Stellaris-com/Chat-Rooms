package com.stellaris.Chat_Rooms.websocket.mappers;

import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import com.stellaris.Chat_Rooms.websocket.dto.send.SendMessageWebSocketSend;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebSocketMessageMapper {
    SendMessageWebSocketSend map(MessageEntity message);
}
