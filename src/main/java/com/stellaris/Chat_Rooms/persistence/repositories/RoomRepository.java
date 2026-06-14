package com.stellaris.Chat_Rooms.persistence.repositories;

import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {
    @Query("""
        SELECT r FROM RoomEntity r
        INNER JOIN r.membersOfRoom mr
        INNER JOIN mr.user u
        WHERE r.id = :roomId
        AND u.id = :ownerId
        AND mr.typeOfMember = com.stellaris.Chat_Rooms.domain.enums.TypeOfMember.OWNER
    """)
    Optional<RoomEntity> findByOwnerUserIdAndRoomId(@Param("ownerId") UUID ownerId, @Param("roomId") UUID roomId);

    @Query("""
        SELECT r FROM RoomEntity r
        INNER JOIN r.membersOfRoom mr
        INNER JOIN mr.user u
        WHERE r.id = :roomId
        AND u.id = :userId
    """)
    Optional<RoomEntity> findByUserIdAndRoomId(@Param("userId") UUID userId, @Param("roomId") UUID roomId);
}
