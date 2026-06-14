package com.stellaris.Chat_Rooms.persistence.repositories;

import com.stellaris.Chat_Rooms.persistence.entities.MembersOfRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MembersOfRoomRepository extends JpaRepository<MembersOfRoomEntity, UUID> {
}
