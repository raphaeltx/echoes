package com.raph.resistance_echoes.util;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

/**
 * Utility class for handling exceptions and logging error messages.
 */
@Slf4j
public class ExceptionUtil {

    /**
     * Handles an error by logging it and throwing a custom exception.
     *
     * @param action           the action being performed when the error occurred
     * @param cause            the exception that caused the error
     * @param exceptionFactory a function that creates a custom exception based on the error message and cause
     * @param <T>              the type of the custom exception
     */
    public static <T extends Exception> T buildAndLogError(
            String action, Exception cause, BiFunction<String, Throwable, T> exceptionFactory) {

        String errorMessage = "Failed to " + action + ": " + cause.getMessage();
        log.error(errorMessage, cause);
        return exceptionFactory.apply(errorMessage, cause);
    }
}
