package com.lasath.batch.configuration.account;

import com.lasath.batch.configuration.DetailsProcessor;
import com.lasath.batch.dao.entity.AccountDetails;
import org.springframework.batch.item.ItemProcessor;

public class AccountDetailsProcessor<AccountDetails> implements DetailsProcessor<AccountDetails> {

    @Override
    public AccountDetails process(final AccountDetails accountDetails) {
       /* final BigDecimal volt = voltage.getVolt();
        final double time = voltage.getTime();

        final AccountDetails processedVoltage = new Voltage();
        processedVoltage.setVolt(volt);
        processedVoltage.setTime(time);*/
        return accountDetails;
    }
}
