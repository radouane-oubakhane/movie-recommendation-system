package com.radouaneoubakhane.userservice.exception;


import com.radouaneoubakhane.userservice.exception.actor.ActorNotFoundException;
import com.radouaneoubakhane.userservice.exception.director.DirectorNotFoundException;
import com.radouaneoubakhane.userservice.exception.movie.MovieNotFoundException;
import com.radouaneoubakhane.userservice.exception.profile.ProfileNotFoundException;
import com.radouaneoubakhane.userservice.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            ActorNotFoundException.class,
            DirectorNotFoundException.class,
            MovieNotFoundException.class,
            ProfileNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ErrorDetails> handleNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    // Handle user bad request
    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    public ResponseEntity<ErrorDetails> handleBadRequestException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorDetails);
    }

}
