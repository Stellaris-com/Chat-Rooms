package com.stellaris.Chat_Rooms.persistence.repositories;

import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
    @Query("""
        SELECT m FROM MessageEntity m
        INNER JOIN m.user u
        INNER JOIN m.room r
        WHERE u.id = :userId
        AND r.id = :roomId
        ORDER BY m.createdAt
    """)
    List<MessageEntity> findAllByRoomIdAndUserId(@Param("roomId") UUID roomId, @Param("userId") UUID userId);

    @Query("""
        SELECT COUNT(m) FROM MessageEntity m
        INNER JOIN m.user u
        INNER JOIN m.room r
        WHERE u.id = :userId
        AND r.id = :roomId
    """)
    int countMessagesByUserIdAndRoomId(@Param("userId") UUID userId, @Param("roomId") UUID roomId);
}
