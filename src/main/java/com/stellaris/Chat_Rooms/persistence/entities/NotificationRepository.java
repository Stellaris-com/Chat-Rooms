package com.stellaris.Chat_Rooms.persistence.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    @Query("""
        SELECT n FROM NotificationEntity n
        INNER JOIN n.user u
        WHERE u.id = :userId
    """)
    List<NotificationEntity> findAllByUserId(@Param("userId") UUID userId);
}
