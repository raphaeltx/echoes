package com.raph.resistance_echoes.exception;

/**
 * Custom exception class for handling errors related to scheduling echoes.
 */
public class ScheduleEchoesException extends Exception {
    public ScheduleEchoesException(String message, Throwable cause) {
        super(message, cause);
    }
}
