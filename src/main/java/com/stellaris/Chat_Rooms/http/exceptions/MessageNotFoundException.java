package com.stellaris.Chat_Rooms.http.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(String message) {
        super(message);
    }

    public MessageNotFoundException() {
        super("Mensagem não foi encontrada");
    }
}
