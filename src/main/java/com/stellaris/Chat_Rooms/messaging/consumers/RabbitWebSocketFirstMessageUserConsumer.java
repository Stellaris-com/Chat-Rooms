package com.stellaris.Chat_Rooms.messaging.consumers;

import com.stellaris.Chat_Rooms.business.helpers.message.FindMessageHelper;
import com.stellaris.Chat_Rooms.messaging.dto.SendMessageEvent;
import com.stellaris.Chat_Rooms.persistence.entities.MessageEntity;
import com.stellaris.Chat_Rooms.websocket.business.SendUserMessageWebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitWebSocketFirstMessageUserConsumer {
    private final SendUserMessageWebSocketService sendUserMessageWebSocketService;
    private final FindMessageHelper findMessageHelper;

    @RabbitListener(queues = "${rabbitmq.web-socket.messages.queue}")
    public void handlerFirstMessage(SendMessageEvent event) {
        MessageEntity messageFound = findMessageHelper.find(event.messageId());
        sendUserMessageWebSocketService.sendMessage(messageFound);
    }
}
