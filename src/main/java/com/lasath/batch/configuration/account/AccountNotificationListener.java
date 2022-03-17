package com.lasath.batch.configuration.account;

import com.lasath.batch.configuration.NotificationListener;
import com.lasath.batch.dao.entity.AccountDetails;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountNotificationListener extends JobExecutionListenerSupport implements NotificationListener<AccountDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    private final EntityManager entityManager;

    @Autowired
    public AccountNotificationListener(final JdbcTemplate jdbcTemplate, final EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results Acc");

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            var query  = cb.createQuery(AccountDetails.class);
            var root = query.from(AccountDetails.class);
            query.select(root);
            List<AccountDetails> result = entityManager.createQuery(query).getResultList();

            for (AccountDetails a:result){
                LOGGER.info("Acc : "+a.toString());
            }
            /*List<AccountDetails> accountDetails = (List<AccountDetails>) entityManager.createQuery("Select  from " +
                                                                                                       "AccountDetails");
            entityManager.detach(accountDetails);*/


            /*jdbcTemplate.query("SELECT id, city FROM account_details",
                    (rs, row) -> new AccountDetails(
                            rs.getString(1),
                            rs.getString(2))
            ).forEach(accountDetails ->
                                                  LOGGER.info("Found <" + accountDetails + "> in the database."));*/
        }
    }
}
