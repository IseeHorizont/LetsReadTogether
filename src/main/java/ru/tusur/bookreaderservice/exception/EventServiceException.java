package ru.tusur.bookreaderservice.exception;

public class EventServiceException extends RuntimeException {

    public EventServiceException() {
    }

    public EventServiceException(String message) {
        super(message);
    }
}
