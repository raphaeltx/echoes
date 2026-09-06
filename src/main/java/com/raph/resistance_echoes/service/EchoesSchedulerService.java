package com.raph.resistance_echoes.service;

import com.raph.resistance_echoes.exception.ScheduleEchoesException;
import com.raph.resistance_echoes.exception.ScheduleJobDuplicatedException;
import com.raph.resistance_echoes.job.EchoesJob;
import com.raph.resistance_echoes.model.dto.ScheduleEchoesDto;
import com.raph.resistance_echoes.model.interfaces.IEchoesSchedulerService;
import com.raph.resistance_echoes.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EchoesSchedulerService implements IEchoesSchedulerService {

    private static final String JOB_GROUP = "echoes-group";
    private static final String PAYLOAD_KEY = "payload";

    private final Scheduler scheduler;

    @Transactional(rollbackOn = ScheduleEchoesException.class)
    @Override
    public void scheduleEchoes(ScheduleEchoesDto scheduleEchoesDto) throws ScheduleEchoesException {
        try {
            validateCronExpression(scheduleEchoesDto.getCronExpression().getCode());
            String jobKeyName = buildJobKeyName(scheduleEchoesDto.getId());
            JobDetail job = buildJob(jobKeyName, scheduleEchoesDto.getPayload());

            validateDuplicateJob(job.getKey());
            Trigger trigger = buildTrigger(jobKeyName, scheduleEchoesDto.getCronExpression().getCode());

            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            throw ExceptionUtil.buildAndLogError("schedule echoes", e, ScheduleEchoesException::new);
        }
    }

    /**
     * Validates the provided cron expression.
     *
     * @param cronExpression the cron expression to validate
     * @throws ScheduleEchoesException if the cron expression is invalid
     */
    private void validateCronExpression(String cronExpression) throws ScheduleEchoesException {
        try {
            CronScheduleBuilder.cronSchedule(cronExpression);
        } catch (Exception e) {
            throw new ScheduleEchoesException("Invalid cron expression: " + cronExpression, e);
        }
    }

    /**
     * Validates if a job with the specified job key already exists in the scheduler.
     *
     * @param jobKey the JobKey to check for duplicates
     * @throws ScheduleJobDuplicatedException if a job with the specified key already exists
     */
    private void validateDuplicateJob(JobKey jobKey) throws ScheduleJobDuplicatedException {
        try {
            if (scheduler.checkExists(jobKey)) {
                throw new ScheduleJobDuplicatedException("Job with key " + jobKey + " already exists");
            }
        } catch (SchedulerException e) {
            throw new ScheduleJobDuplicatedException("Error checking for duplicate job: " + e.getMessage());
        }
    }

    /**
     * Builds a job key name with the specified ID.
     *
     * @param id the unique identifier for the job
     * @return a string representing the job key name
     */
    private String buildJobKeyName(Integer id) {
        return "echoJob-" + id + "-" + UUID.randomUUID();
    }

    /**
     * Builds a job with the specified job key name and payload.
     *
     * @param jobKeyName the name of the job key
     * @param payload    the payload to be passed to the job
     * @return a JobDetail instance configured with the specified job key name and payload
     */
    private JobDetail buildJob(String jobKeyName, String payload) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(PAYLOAD_KEY, payload);

        return JobBuilder.newJob(EchoesJob.class)
                .withIdentity(jobKeyName, JOB_GROUP)
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    /**
     * Builds a trigger for the job with the specified job key name.
     *
     * @param jobKeyName the name of the job key
     * @return a Trigger instance configured to fire based on the cron expression
     */
    private Trigger buildTrigger(String jobKeyName, String cronExpression) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobKeyName + "-trigger", JOB_GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }
}
