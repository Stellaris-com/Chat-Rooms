package com.stellaris.Chat_Rooms.business.useCases.room;

import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomResponseDTO> list() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::map)
                .toList();
    }
}
