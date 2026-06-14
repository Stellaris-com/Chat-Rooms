package com.stellaris.Chat_Rooms.business.room;

import com.stellaris.Chat_Rooms.domain.enums.TypeOfMember;
import com.stellaris.Chat_Rooms.http.exceptions.RoomNotFoundException;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.mappers.RoomMapper;
import com.stellaris.Chat_Rooms.persistence.repositories.MembersOfRoomRepository;
import com.stellaris.Chat_Rooms.persistence.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExitRoomService {
    private final RoomRepository roomRepository;
    private final MembersOfRoomRepository membersOfRoomRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public void exit(UserEntity currentUser, UUID roomId) {
        RoomEntity roomFound = roomRepository.findByUserIdAndRoomId(currentUser.getId(), roomId)
                .orElseThrow(RoomNotFoundException::new);

        roomFound.getMembersOfRoom().removeIf(m -> m.getUser().getId().equals(currentUser.getId()));

        if (roomFound.getMembersOfRoom().isEmpty()) {
            roomRepository.deleteById(roomFound.getId());
            return;
        }

        else roomFound.getMembersOfRoom().getFirst().setTypeOfMember(TypeOfMember.OWNER);

        roomMapper.map(roomRepository.save(roomFound));
    }
}
