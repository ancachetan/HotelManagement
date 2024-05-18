package com.internship.hotelmanagementbackend.exception.handler;

import com.internship.hotelmanagementbackend.exception.AlreadyExistingUserException;
import com.internship.hotelmanagementbackend.exception.BookingCancelException;
import com.internship.hotelmanagementbackend.exception.BookingUpdateException;
import com.internship.hotelmanagementbackend.exception.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import static com.internship.hotelmanagementbackend.util.ExceptionMessages.VALIDATION_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ApiExceptionResponse> handleNotFound(Exception exception) {
        return new ResponseEntity<>(getExceptionBody(exception.getMessage(), NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler({AlreadyExistingUserException.class,
            BookingUpdateException.class,
            BookingCancelException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiExceptionResponse> handleConflict(Exception exception) {
        return new ResponseEntity<>(getExceptionBody(exception.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
        ApiExceptionResponse apiExceptionResponse = getExceptionBody(VALIDATION_EXCEPTION_MESSAGE, BAD_REQUEST);
        apiExceptionResponse.setErrors(errors);
        return new ResponseEntity<>(apiExceptionResponse, BAD_REQUEST);
    }

    private ApiExceptionResponse getExceptionBody(String message, HttpStatus status) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse();
        apiExceptionResponse.setCurrentTime(LocalDateTime.now());
        apiExceptionResponse.setMessage(message);
        apiExceptionResponse.setStatus(status.value());
        return apiExceptionResponse;
    }
}
