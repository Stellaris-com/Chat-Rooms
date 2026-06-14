package com.stellaris.Chat_Rooms.business.useCases.room;

import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.MembersOfRoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnterInRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomResponseDTO enter(UserEntity user, UUID roomId) {
        RoomEntity roomFound = roomRepository.findById(roomId)
                .orElseThrow(RoomNotFoundException::new);

        roomFound.getMembersOfRoom().add(MembersOfRoomEntity.buildMemberParticipant(user, roomFound));

        return roomMapper.map(roomRepository.save(roomFound));
    }
}
