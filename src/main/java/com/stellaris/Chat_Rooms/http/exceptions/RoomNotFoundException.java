package com.stellaris.Chat_Rooms.http.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException() {
        super("Sala não foi encontrada");
    }
}
