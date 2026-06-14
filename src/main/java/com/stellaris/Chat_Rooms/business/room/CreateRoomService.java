package com.stellaris.Chat_Rooms.business.room;

import com.stellaris.Chat_Rooms.business.user.IncreaseUserRoomsCreatedService;
import com.stellaris.Chat_Rooms.http.dto.request.room.CreateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.MembersOfRoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import com.stellaris.Chat_Rooms.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateRoomService {
    private final IncreaseUserRoomsCreatedService increaseUserRoomsCreatedService;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public RoomResponseDTO create(UserEntity currentUser, CreateRoomRequestDTO request) {
        RoomEntity createRoom = roomMapper.map(request);
        createRoom.setMembersOfRoom(new ArrayList<>());
        createRoom.getMembersOfRoom().add(MembersOfRoomEntity.buildMemberOwner(currentUser, createRoom));
        createRoom.setSimpleDescription("Criada por " + currentUser.getUsername());

        RoomEntity createdRoom = roomRepository.save(createRoom);

        increaseUserRoomsCreatedService.increaseRoomsCreated(currentUser);

        return roomMapper.map(createRoom);
    }
}
