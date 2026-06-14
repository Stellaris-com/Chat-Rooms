package com.stellaris.Chat_Rooms.persistence.repositories;

import com.stellaris.Chat_Rooms.persistence.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {
    @Query("""
        SELECT t FROM TokenEntity t
        INNER JOIN t.user u
        WHERE u.id = :userId
    """)
    Optional<TokenEntity> findByUserId(@Param("userId") UUID userId);

    @Modifying
    @Query("""
        DELETE FROM TokenEntity t
        WHERE t.user.id = :userId
    """)
    void deleteByUserId(@Param("userId") UUID userId);
}
