package com.stellaris.Chat_Rooms.business.helpers.message;

import com.stellaris.Chat_Rooms.http.exceptions.MessageNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindMessageHelper {
    private final MessageRepository messageRepository;

    public MessageEntity find(UUID messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(MessageNotFoundException::new);
    }
}
