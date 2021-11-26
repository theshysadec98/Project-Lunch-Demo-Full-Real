package com.example.exception;

import lombok.Getter;

public class BadRequest extends RuntimeException{
    @Getter
    private String code;

    public BadRequest() {
        super("Invalid request");
    }

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(String message, String code) {
        super(message);
        this.code = code;
    }
}
