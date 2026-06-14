package com.stellaris.Chat_Rooms.http.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException() {
        super("Token não foi encontrado");
    }
}
