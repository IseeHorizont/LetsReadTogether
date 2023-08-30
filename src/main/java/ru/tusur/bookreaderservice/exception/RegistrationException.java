package ru.tusur.bookreaderservice.exception;

public class RegistrationException extends RuntimeException {

    public RegistrationException() {
    }

    public RegistrationException(String message) {
        super(message);
    }
}
