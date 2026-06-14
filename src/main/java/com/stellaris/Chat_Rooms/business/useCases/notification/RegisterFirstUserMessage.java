package com.stellaris.Chat_Rooms.business.useCases.notification;

import com.stellaris.Chat_Rooms.business.helpers.notification.RegisterNotificationHelper;
import com.stellaris.Chat_Rooms.persistence.entities.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.MessageRepository;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterFirstUserMessage {
    private final RegisterNotificationHelper registerNotificationHelper;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public void registerFirstUserMessage(UserEntity currentUser, RoomEntity room) {
        if (this.userIsSendMessages(currentUser.getId(), room.getId())) return;

        registerNotificationHelper.register(room, currentUser.getUsername() + " enviou sua primeira mensagem para sala " + room.getName());
    }
    private boolean userIsSendMessages(UUID userId, UUID roomId) {
        return messageRepository.countMessagesByUserIdAndRoomId(userId, roomId) > 0;
    }
}
