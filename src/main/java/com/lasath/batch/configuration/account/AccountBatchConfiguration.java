package com.lasath.batch.configuration.account;

import com.lasath.batch.configuration.BatchConfiguration;
import com.lasath.batch.dao.entity.AccountDetails;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@Configuration
@EnableBatchProcessing
public class AccountBatchConfiguration implements BatchConfiguration<AccountDetails> {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Override
    @Bean
    public FlatFileItemReader<AccountDetails> reader() {
        return new FlatFileItemReaderBuilder<AccountDetails>()
                .name("accountDetailsReader")
//                .resource(new ClassPathResource("accountDetails.csv"))
                .resource(new ClassPathResource("accountDetails10.csv"))
                .delimited()
                .names(new String[]
                           {
                               "method", "type", "activityId", "firstName",
                               "vatNumber", "referenceNumber", "secondName",
                               "thirdName", "lastName", "idType", "idNumber",
                               "phoneNumber", "email" , "gender", "streetName",
                               "buildingNumber", "district", "city", "additionalNumber"
                           })
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<AccountDetails>() {{
                    setTargetType(AccountDetails.class);
                }})
                .build();
    }

    @Override
    @Bean
    public LineMapper<AccountDetails> lineMapper() {

        final DefaultLineMapper<AccountDetails> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"method", "type", "activityId", "firstName",
            "vatNumber", "referenceNumber", "secondName", "thirdName", "lastName", "idType", "idNumber", "phoneNumber", "email"
            , "gender", "streetName", "buildingNumber", "district", "city", "additionalNumber"});

        final AccountFieldSetMapper fieldSetMapper = new AccountFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Override
    @Bean
    public AccountDetailsProcessor processor() {
        return new AccountDetailsProcessor();
    }

    @Override
    @Bean
    public JdbcBatchItemWriter<AccountDetails> writer(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AccountDetails>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO account_details (method, type, activity_id, first_name," +
                         "vat_number, reference_number, second_name, third_name, last_name, id_type, id_number, " +
                         "phone_number, email" +
                         ", gender, street_name, building_number, district, city, additional_number) VALUES" +
                         "(:method, :type, :activityId, :firstName, :vatNumber, :referenceNumber, :secondName, " +
                         ":thirdName, :lastName, :idType, :idNumber, :phoneNumber, :email" +
                         " , :gender, :streetName, :buildingNumber, :district, :city, :additionalNumber)")
                .dataSource(dataSource)
                .build();
    }

//    @Override
    @Bean
    public Job importAccountJob(AccountNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importAccountJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Override
    @Bean
    public Step step1(JdbcBatchItemWriter<AccountDetails> writer) {
        log.info("############## Step 1 Initiated " + System.currentTimeMillis());
        return stepBuilderFactory.get("step1")
                .<AccountDetails, AccountDetails> chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
