package com.raph.resistance_echoes.model.dto;

import com.raph.resistance_echoes.model.constants.ScheduleRecurrenceEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a request to save echoes with a message.
 */
@Getter
@Setter
public class SaveEchoesRequest {

    /**
     * The message to be saved as echoes.
     */
    @NotNull(message = "Message is required")
    private String message;

    /**
     * The recurrence schedule for the echoes.
     */
    @NotNull(message = "Schedule recurrence is required")
    private ScheduleRecurrenceEnum scheduleRecurrenceEnum;
}
