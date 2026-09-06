package com.raph.resistance_echoes.model.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScheduleRecurrenceEnum defines the various recurrence patterns for scheduling tasks.
 * Each enum constant represents a specific cron expression that can be used to schedule jobs.
 */
@Getter
@RequiredArgsConstructor
public enum ScheduleRecurrenceEnum {

    EVERY_MINUTE("0 * * * * ?"),
    EVERY_HOUR("0 0 * * * ?"),
    EVERY_DAY("0 0 0 * * ?"),
    EVERY_WEEK("0 0 0 ? * MON"),
    EVERY_MONTH("0 0 0 1 * ?");

    private final String code;
}
