package com.stellaris.Chat_Rooms.business.useCases.notification;

import com.stellaris.Chat_Rooms.http.dto.response.notification.NotificationResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.NotificationRepository;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListNotificationsService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationResponseDTO> list(UserEntity currentUser, UUID roomId) {
        return notificationRepository.findAllByUserIdAndRoomId(currentUser.getId(), roomId)
                .stream()
                .map(notificationMapper::map)
                .toList();
    }
}
