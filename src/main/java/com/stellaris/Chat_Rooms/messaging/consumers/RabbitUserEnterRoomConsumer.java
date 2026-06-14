package com.stellaris.Chat_Rooms.messaging.consumers;

import com.stellaris.Chat_Rooms.business.useCases.notification.RegisterUserEnterInRoom;
import com.stellaris.Chat_Rooms.messaging.dto.UserEnterAndExitRoomEvent;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitUserEnterRoomConsumer {
    private final RegisterUserEnterInRoom registerUserEnterInRoom;

    @RabbitListener
    public void handlerUserEnterRoomEvent(UserEnterAndExitRoomEvent event) {

    }
}
