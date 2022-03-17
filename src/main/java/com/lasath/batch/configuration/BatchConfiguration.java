package com.lasath.batch.configuration;

import com.lasath.batch.dao.entity.AccountDetails;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.context.annotation.Bean;

public interface BatchConfiguration<T> {

    @Bean
    FlatFileItemReader<T> reader();

    @Bean
    LineMapper<T> lineMapper();

    @Bean
    DetailsProcessor processor();

    @Bean
    JdbcBatchItemWriter<T> writer(DataSource dataSource);

 /*   @Bean
    Job importAccountJob(NotificationListener listener, Step step1);*/

    @Bean
    Step step1(JdbcBatchItemWriter<T> writer);
}
