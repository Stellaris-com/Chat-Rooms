package com.stellaris.Chat_Rooms.business.room;

import com.stellaris.Chat_Rooms.http.dto.request.room.CreateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.MembersOfRoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.MembersOfRoomRepository;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateRoomService {
    private final RoomRepository roomRepository;
    private final MembersOfRoomRepository membersOfRoomRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public RoomResponseDTO create(UserEntity currentUser, CreateRoomRequestDTO request) {
        RoomEntity createRoom = roomMapper.map(request);
        createRoom.setMembersOfRoom(new ArrayList<>());
        createRoom.getMembersOfRoom().add(MembersOfRoomEntity.buildMemberOwner(currentUser, createRoom));

        RoomEntity createdRoom = roomRepository.save(createRoom);
        return roomMapper.map(createRoom);
    }
}
