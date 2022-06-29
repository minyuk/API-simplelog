package com.simplelog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class SimplelogException extends RuntimeException{

    private final Map<String, String> validation = new HashMap<>();

    public SimplelogException(String message) {
        super(message);
    }

    public SimplelogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatus();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
