package com.stellaris.Chat_Rooms.persistence.repositories;

import com.stellaris.Chat_Rooms.persistence.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    @Query("""
        SELECT n FROM NotificationEntity n
        INNER JOIN n.room r
        INNER JOIN r.membersOfRoom mr
        INNER JOIN mr.user u
        WHERE u.id = :userId
        AND r.id = :roomId
    """)
    List<NotificationEntity> findAllByUserIdAndRoomId(@Param("userId") UUID userId, @Param("roomId") UUID roomId);
}
