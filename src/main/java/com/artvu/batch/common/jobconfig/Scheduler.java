package com.artvu.batch.common.jobconfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher jobLauncher;

    private final ApiBatchConfig config;

    // 매일 자정
    @Scheduled(cron = "0 0 0 * * *")
    public void dataScheduling() throws JobInstanceAlreadyExistsException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobInstanceAlreadyCompleteException {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        JobParameters parameters = new JobParametersBuilder()
                .addString("jobName", "artJob-"+now)
                .toJobParameters();
        jobLauncher.run(config.artReaderJob(), parameters);
    }

}
