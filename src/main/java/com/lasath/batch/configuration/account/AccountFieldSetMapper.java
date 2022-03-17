package com.lasath.batch.configuration.account;

import com.lasath.batch.dao.entity.AccountDetails;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class AccountFieldSetMapper implements com.lasath.batch.configuration.FieldSetMapper<AccountDetails> {

    @Override
    public AccountDetails mapFieldSet(FieldSet fieldSet) {

        final AccountDetails accountDetails = new AccountDetails();

        accountDetails.setMethod(fieldSet.readRawString("method"));
        accountDetails.setActivityId(fieldSet.readLong("activityId"));
        accountDetails.setFirstName(fieldSet.readRawString("firstName"));
        accountDetails.setThirdName(fieldSet.readRawString("thirdName"));
        accountDetails.setLastName(fieldSet.readRawString("lastName"));
        accountDetails.setIdType(fieldSet.readRawString("idType"));
        accountDetails.setIdNumber(fieldSet.readRawString("idNumber"));
        accountDetails.setPhoneNumber(fieldSet.readRawString("phoneNumber"));
        accountDetails.setEmail(fieldSet.readRawString("email"));
        accountDetails.setGender(fieldSet.readRawString("gender"));
        accountDetails.setBuildingNumber(fieldSet.readRawString("buildingNumber"));
        accountDetails.setDistrict(fieldSet.readRawString("district"));
        accountDetails.setCity(fieldSet.readLong("city"));
        accountDetails.setAdditionalNumber(fieldSet.readRawString("additionalNumber"));

        return accountDetails;

    }
}
