package com.stellaris.Chat_Rooms.business.useCases.message;

import com.stellaris.Chat_Rooms.http.dto.request.message.SendMessageRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.message.MessageResponseDTO;
import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.MessageMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.MessageRepository;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendMessageService {
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final MessageMapper messageMapper;

    public MessageResponseDTO send(UserEntity currentUser, UUID roomId, @Valid SendMessageRequestDTO request) {
        MessageEntity preSendMessage = messageMapper.map(request);
        preSendMessage.setUser(currentUser);

        RoomEntity roomFound = roomRepository.findByUserIdAndRoomId(currentUser.getId(), roomId)
                .orElseThrow(RoomNotFoundException::new);

        preSendMessage.setRoom(roomFound);

        return messageMapper.map(messageRepository.save(preSendMessage));
    }
}
