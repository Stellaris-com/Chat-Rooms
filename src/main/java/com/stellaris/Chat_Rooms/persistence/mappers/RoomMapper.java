package com.stellaris.Chat_Rooms.persistence.mappers;

import com.stellaris.Chat_Rooms.http.dto.request.room.CreateRoomRequestDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomResponseDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.RoomUserResponseDTO;
import com.stellaris.Chat_Rooms.http.dto.response.room.SimpleRoomResponseDTO;
import com.stellaris.Chat_Rooms.persistence.entities.MembersOfRoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity map(CreateRoomRequestDTO request);

    @Mapping(target = "membersOfRoom", expression = "java(membersOfRoomToResponse(entity.getMembersOfRoom()))")
    RoomResponseDTO map(RoomEntity entity);

    SimpleRoomResponseDTO mapToSimple(RoomEntity entity);

    default List<RoomUserResponseDTO> membersOfRoomToResponse(List<MembersOfRoomEntity> membersOfRoomEntity) {
        return membersOfRoomEntity.stream()
                .map(m -> {
                    UserEntity user = m.getUser();
                    return new RoomUserResponseDTO(user.getId(), user.getUsername(), m.getTypeOfMember());
                })
                .toList();
    }
}
