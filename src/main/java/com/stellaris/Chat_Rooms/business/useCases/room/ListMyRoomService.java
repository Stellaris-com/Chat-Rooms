package com.stellaris.Chat_Rooms.business.useCases.room;

import com.stellaris.Chat_Rooms.http.dto.response.room.SimpleRoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListMyRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<SimpleRoomResponseDTO> listMyRooms(UserEntity currentUser) {
        return roomRepository.findAllByUserId(currentUser.getId())
                .stream()
                .map(roomMapper::mapToSimple)
                .toList();
    }
}
