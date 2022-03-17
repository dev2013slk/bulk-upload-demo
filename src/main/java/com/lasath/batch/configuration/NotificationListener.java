package com.lasath.batch.configuration;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public interface NotificationListener<AccountDetails> extends JobExecutionListener {

    @Override
    void afterJob(JobExecution jobExecution);
}
