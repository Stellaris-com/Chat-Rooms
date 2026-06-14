package com.stellaris.Chat_Rooms.business.helpers.room;

import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindRoomHelper {
    private final RoomRepository roomRepository;

    public RoomEntity find(UUID roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(RoomNotFoundException::new);
    }
}
