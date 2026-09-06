package com.raph.resistance_echoes.exception;

/**
 * Exception thrown when a schedule job is duplicated.
 */
public class ScheduleJobDuplicatedException extends Exception {
    public ScheduleJobDuplicatedException(String message) {
        super(message);
    }
}
