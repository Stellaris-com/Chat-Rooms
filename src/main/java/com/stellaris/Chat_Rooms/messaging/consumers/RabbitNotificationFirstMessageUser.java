package com.stellaris.Chat_Rooms.messaging.consumers;

import com.stellaris.Chat_Rooms.business.helpers.room.FindRoomByUserHelper;
import com.stellaris.Chat_Rooms.business.helpers.user.FindUserHelper;
import com.stellaris.Chat_Rooms.business.useCases.notification.RegisterFirstUserMessage;
import com.stellaris.Chat_Rooms.messaging.dto.SendMessageEvent;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitNotificationFirstMessageUser {
    private final RegisterFirstUserMessage registerFirstUserMessage;
    private final FindUserHelper findUserHelper;
    private final FindRoomByUserHelper findRoomByUserHelper;

    @RabbitListener(queues = "${rabbitmq.notification.first.message.queue}")
    public void handlerFirstMessage(SendMessageEvent event) {
        UserEntity userFound = findUserHelper.findUser(event.userId());
        RoomEntity roomFound = findRoomByUserHelper.findByUser(event.userId(), event.roomId());

        registerFirstUserMessage.registerFirstUserMessage(userFound, roomFound);
    }
}
