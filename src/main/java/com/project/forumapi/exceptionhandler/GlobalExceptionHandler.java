package com.project.forumapi.exceptionhandler;

import com.project.forumapi.exception.MatterNotFoundException;
import com.project.forumapi.exception.PersonNotFoundException;
import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.exception.WithoutPermissionException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorDescription.Field> fields = new ArrayList<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new ErrorDescription.Field(name, message));
        }

        ErrorDescription errorDescription = ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title("One or more fields are invalid. Fill in correctly and try again.")
                .fields(fields)
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFound(PersonNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription = ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(MatterNotFoundException.class)
    public ResponseEntity<Object> handleMatterNotFound(MatterNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription = ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Object> handleTopicNotFound(TopicNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDescription errorDescription = ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

    @ExceptionHandler(WithoutPermissionException.class)
    public ResponseEntity<Object> handleWithoutPermission(WithoutPermissionException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        ErrorDescription errorDescription = ErrorDescription
                .builder()
                .status(status.value())
                .time(OffsetDateTime.now())
                .title(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorDescription);
    }

}
