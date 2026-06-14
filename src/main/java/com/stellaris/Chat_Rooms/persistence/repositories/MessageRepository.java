package com.stellaris.Chat_Rooms.persistence.repositories;

import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
