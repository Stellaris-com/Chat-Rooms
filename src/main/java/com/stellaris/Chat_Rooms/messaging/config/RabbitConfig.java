package com.stellaris.Chat_Rooms.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${rabbitmq.chat.exchange}")
    private String chatExchange;

    @Value("${rabbitmq.room.exchange}")
    private String roomExchange;

    @Value("${rabbitmq.message.send.routing.key}")
    private String messageSendRoutingKey;

    @Value("${rabbitmq.user.enter.room.routing.key}")
    private String userEnterRoomRoutingKey;

    @Value("${rabbitmq.user.exit.room.routing.key}")
    private String userExitRoomRoutingKey;

    @Value("${rabbitmq.web-socket.messages.queue}")
    private String webSocketMessagesQueue;

    @Value("${rabbitmq.notification.first.message.queue}")
    private String notificationFirstMessageQueue;

    @Value("${rabbitmq.notification.user.enter.room.queue}")
    private String notificationUserEnterRoomQueue;

    @Value("${rabbitmq.notification.user.exit.room.queue}")
    private String notificationUserExitRoomQueue;

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitListener(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(messageConverter);
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public TopicExchange chatExchange() {
        return new TopicExchange(chatExchange);
    }

    @Bean
    public TopicExchange roomExchange() {
        return new TopicExchange(roomExchange);
    }

    @Bean
    public Queue webSocketMessagesQueue() {
        return new Queue(webSocketMessagesQueue);
    }

    @Bean
    public Queue notificationFirstMessageQueue() {
        return new Queue(notificationFirstMessageQueue);
    }

    @Bean
    public Queue notificationUserEnterRoomQueue() {
        return new Queue(notificationUserEnterRoomQueue);
    }

    @Bean
    public Queue notificationUserExitRoomQueue() {
        return new Queue(notificationUserExitRoomQueue);
    }


    @Bean
    public Binding webSocketMessagesBinding() {
        return BindingBuilder.bind(this.webSocketMessagesQueue()).to(this.chatExchange()).with(messageSendRoutingKey);
    }

    @Bean
    public Binding notificationFirstMessageBinding() {
        return BindingBuilder.bind(this.notificationFirstMessageQueue()).to(this.chatExchange()).with(messageSendRoutingKey);
    }

    @Bean
    public Binding notificationUserEnterRoomBinding() {
        return BindingBuilder.bind(this.notificationUserEnterRoomQueue()).to(this.roomExchange()).with(userEnterRoomRoutingKey);
    }

    @Bean
    public Binding notificationUserExitRoomBinding() {
        return BindingBuilder.bind(this.notificationUserExitRoomQueue()).to(this.roomExchange()).with(userExitRoomRoutingKey);
    }
}


