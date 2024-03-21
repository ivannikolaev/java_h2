package ru.tinkoff.messages.error;

public class BadMessageException extends RuntimeException {
    public BadMessageException(String message) {
        super(message);
    }
}
