package com.raph.resistance_echoes.exception;

import com.raph.resistance_echoes.model.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown by controllers and returns appropriate API responses.
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles SaveEchoesException and returns an ApiResponse with the error message.
     *
     * @param ex the SaveEchoesException thrown
     * @return an ApiResponse containing the error message
     */
    @ExceptionHandler(SaveEchoesException.class)
    public ApiResponse<Void> handleSaveEchoesException(SaveEchoesException ex) {
        return logException(ex, ex.getMessage());
    }

    /**
     * Handles ScheduleEchoesException and returns an ApiResponse with the error message.
     *
     * @param ex the ScheduleEchoesException thrown
     * @return an ApiResponse containing the error message
     */
    @ExceptionHandler(ScheduleEchoesException.class)
    public ApiResponse<Void> handleScheduleEchoesException(ScheduleEchoesException ex) {
        return logException(ex, ex.getMessage());
    }

    /**
     * Handles generic exceptions and returns an ApiResponse with the error message.
     *
     * @param ex the Exception thrown
     * @return an ApiResponse containing the error message
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleGenericException(Exception ex) {
        return logException(ex, "An unexpected error occurred");
    }

    /**
     * Logs the exception and returns an ApiResponse with the error message.
     *
     * @param ex      the Exception to log
     * @param message the error message to include in the ApiResponse
     * @return an ApiResponse containing the error message
     */
    private ApiResponse<Void> logException(Exception ex, String message) {
        log.error(message, ex);
        return ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .build();
    }
}
