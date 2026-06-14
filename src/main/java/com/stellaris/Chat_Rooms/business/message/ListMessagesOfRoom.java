package com.stellaris.Chat_Rooms.business.message;

import com.stellaris.Chat_Rooms.http.dto.response.message.MessageResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.MessageMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListMessagesOfRoom {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public List<MessageResponseDTO> list(UserEntity currentUser, UUID roomId) {
        return messageRepository.findAllByRoomIdAndUserId(roomId, currentUser.getId())
                .stream()
                .map(messageMapper::map)
                .toList();
    }
}
