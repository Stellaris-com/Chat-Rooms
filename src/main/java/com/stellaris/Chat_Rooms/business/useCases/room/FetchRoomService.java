package com.stellaris.Chat_Rooms.business.useCases.room;

import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FetchRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomResponseDTO fetch(UUID roomId) {
        return roomMapper.map(roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new));
    }
}
