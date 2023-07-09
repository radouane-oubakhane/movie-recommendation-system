package com.radouaneoubakhane.userservice.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
