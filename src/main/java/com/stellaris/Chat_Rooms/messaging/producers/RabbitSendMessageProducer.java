package com.stellaris.Chat_Rooms.messaging.producers;

import com.stellaris.Chat_Rooms.messaging.dto.SendMessageEvent;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitSendMessageProducer {
    @Value("${rabbitmq.chat.exchange}")
    private String chatExchange;

    @Value("${rabbitmq.message.send.routing.key}")
    private String sendMessageRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageEvent(UserEntity user, RoomEntity room) {
        rabbitTemplate.convertAndSend(chatExchange, sendMessageRoutingKey, new SendMessageEvent(user.getId(), room.getId()));
    }
}
