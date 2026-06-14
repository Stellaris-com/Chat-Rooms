package com.stellaris.Chat_Rooms.business.room;

import com.stellaris.Chat_Rooms.http.dto.request.room.UpdateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomResponseDTO update(UserEntity user, UpdateRoomRequestDTO request, UUID roomId) {
        RoomEntity updateRoom = roomRepository.findByOwnerUserIdAndRoomId(user.getId(), roomId)
                .orElseThrow(RoomNotFoundException::new);
        updateRoom.setName(request.name());

        RoomEntity updatedRoom = roomRepository.save(updateRoom);
        return roomMapper.map(updatedRoom);
    }
}
