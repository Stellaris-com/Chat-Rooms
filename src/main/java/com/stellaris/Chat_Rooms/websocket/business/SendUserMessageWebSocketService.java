package com.stellaris.Chat_Rooms.websocket.business;

import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import com.stellaris.Chat_Rooms.websocket.mappers.WebSocketMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendUserMessageWebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final WebSocketMessageMapper webSocketMessageMapper;

    public void sendMessage(MessageEntity message) {
        simpMessagingTemplate.convertAndSend("/topic/rooms/" + message.getRoom().getId(), webSocketMessageMapper.map(message));
    }
}
