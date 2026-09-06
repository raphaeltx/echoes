package com.raph.resistance_echoes.job;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * EchoesJob is a Quartz job that executes a specific task when triggered.
 * This class extends QuartzJobBean and overrides the executeInternal method to define the job's behavior.
 */
public class EchoesJob extends QuartzJobBean {

    /**
     * Executes the job's logic when triggered by the Quartz scheduler.
     *
     * @param context The JobExecutionContext provided by Quartz, containing job details and runtime information.
     */
    @Override
    protected void executeInternal(org.quartz.JobExecutionContext context) {
        System.out.println("Executing EchoesJob..." + context.getJobDetail().getKey());
        System.out.println("Payload: " + context.getMergedJobDataMap().getString("payload"));
    }
}
