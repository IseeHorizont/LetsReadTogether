package ru.tusur.bookreaderservice.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tusur.bookreaderservice.dto.ErrorResponse;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String ERROR_MESSAGE_NOT_VALID_ARGUMENT = "Запрос содержит не валидные параметры";
    private static final String ERROR_MESSAGE_CONSTRAINT_PARAMETER = "Запрос содержит не верный id";
    private static final String ERROR_MESSAGE_USER_NOT_FOUND = "Пользователь не найден";


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            @NonNull final MethodArgumentNotValidException ex
    ) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .errorMessage(ERROR_MESSAGE_NOT_VALID_ARGUMENT)
                        .moreInformation(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            @NonNull final ConstraintViolationException ex
    ) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .errorMessage(ERROR_MESSAGE_CONSTRAINT_PARAMETER)
                        .moreInformation(ex.getLocalizedMessage())
                        .build()
                );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(@NonNull final UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .errorMessage(ERROR_MESSAGE_USER_NOT_FOUND)
                        .build()
                );
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorResponse> handleRegistrationException(@NonNull final RegistrationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .errorMessage(ex.getMessage())
                        .build()
                );
    }
}
