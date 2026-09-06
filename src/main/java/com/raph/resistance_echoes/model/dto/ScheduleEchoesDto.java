package com.raph.resistance_echoes.model.dto;

import com.raph.resistance_echoes.model.constants.ScheduleRecurrenceEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for scheduling echoes.
 * This class contains the necessary information to schedule a job that echoes a payload at specified intervals.
 */
@Getter
@Setter
@Builder
public class ScheduleEchoesDto {

    /**
     * The payload to be echoed by the scheduled job.
     */
    String payload;

    /**
     * The unique identifier for the scheduled job.
     */
    Integer id;

    /**
     * The cron expression defining the schedule for the job.
     */
    ScheduleRecurrenceEnum cronExpression;
}
