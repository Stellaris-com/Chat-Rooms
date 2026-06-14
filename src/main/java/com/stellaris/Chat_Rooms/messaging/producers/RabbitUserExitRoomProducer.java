package com.stellaris.Chat_Rooms.messaging.producers;

import com.stellaris.Chat_Rooms.messaging.dto.UserEnterAndExitRoomEvent;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitUserExitRoomProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.room.exchange}")
    private String roomExchange;

    @Value("${rabbitmq.user.exit.room.routing.key}")
    private String userExitRoomRoutingKey;

    public void userExitRoomEvent(UserEntity user, RoomEntity room) {
        rabbitTemplate.convertAndSend(roomExchange, userExitRoomRoutingKey, new UserEnterAndExitRoomEvent(user.getId(), room.getId()));
    }
}
