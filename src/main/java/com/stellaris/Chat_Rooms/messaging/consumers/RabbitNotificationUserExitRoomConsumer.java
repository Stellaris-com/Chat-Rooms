package com.stellaris.Chat_Rooms.messaging.consumers;

import com.stellaris.Chat_Rooms.business.helpers.room.FindRoomByUserHelper;
import com.stellaris.Chat_Rooms.business.helpers.room.FindRoomHelper;
import com.stellaris.Chat_Rooms.business.helpers.user.FindUserHelper;
import com.stellaris.Chat_Rooms.business.useCases.notification.RegisterUserExitRoom;
import com.stellaris.Chat_Rooms.messaging.dto.UserEnterAndExitRoomEvent;
import com.stellaris.Chat_Rooms.persistence.entities.RoomEntity;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitNotificationUserExitRoomConsumer {
    private final RegisterUserExitRoom registerUserExitRoom;
    private final FindUserHelper findUserHelper;
    private final FindRoomHelper findRoomHelper;

    @RabbitListener(queues = "${rabbitmq.notification.user.exit.room.queue}")
    public void handlerUserExitEvent(UserEnterAndExitRoomEvent event) {
        UserEntity userFound = findUserHelper.findUser(event.userId());
        RoomEntity roomFound = findRoomHelper.find(event.roomId());
        registerUserExitRoom.userExitRoom(userFound, roomFound);
    }
}
