package com.stellaris.Chat_Rooms.business.notification;

import com.stellaris.Chat_Rooms.persistence.entities.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.MessageRepository;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterFirstUserMessage {
    private final NotificationRepository notificationRepository;
    private final RegisterNotificationService registerNotificationService;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public void registerFirstUserMessage(UserEntity currentUser, UUID roomId) {
        Optional<RoomEntity> existingRoom = this.userNotIsAMemberOfRoom(currentUser.getId(), roomId);
        if (existingRoom.isEmpty() && this.userIsSendMessages(currentUser.getId(), roomId)) return;

        RoomEntity roomFound = existingRoom.get();
        registerNotificationService.register(currentUser, currentUser.getUsername() + " enviou sua primeira mensagem para sala " + roomFound.getName());
    }

    private Optional<RoomEntity> userNotIsAMemberOfRoom(UUID userId, UUID roomId) {
        return roomRepository.findByUserIdAndRoomId(userId, roomId);
    }

    private boolean userIsSendMessages(UUID userId, UUID roomId) {
        return messageRepository.countMessagesByUserIdAndRoomId(userId, roomId) > 0;
    }
}
