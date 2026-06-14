package com.stellaris.Chat_Rooms.business.helpers.room;

import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindRoomByUserHelper {
    private final RoomRepository roomRepository;

    public RoomEntity findByUser(UserEntity currentUser, UUID roomId) {
        return this.findByUser(currentUser.getId(), roomId);
    }

    public RoomEntity findByUser(UUID userId, UUID roomId) {
        return roomRepository.findByUserIdAndRoomId(userId, roomId)
                .orElseThrow(RoomNotFoundException::new);
    }
}
