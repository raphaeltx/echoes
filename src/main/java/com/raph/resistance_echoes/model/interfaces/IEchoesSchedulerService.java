package com.raph.resistance_echoes.model.interfaces;

import com.raph.resistance_echoes.exception.ScheduleEchoesException;
import com.raph.resistance_echoes.model.dto.ScheduleEchoesDto;
import org.quartz.SchedulerException;

/**
 * Interface for scheduling echoes.
 */
public interface IEchoesSchedulerService {

    /**
     * Schedules echoes with the given payload.
     *
     * @param scheduleEchoesDto the DTO containing the payload and job details
     * @throws SchedulerException if there is an error scheduling the job
     */
    void scheduleEchoes(ScheduleEchoesDto scheduleEchoesDto) throws SchedulerException, ScheduleEchoesException;
}
