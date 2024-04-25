package com.artvu.batch.common.jobconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher jobLauncher;

    // 매일 자정
    @Scheduled(cron = "0 0 0 * * ?")
    public void dataScheduling() throws JobInstanceAlreadyExistsException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

    }

}
