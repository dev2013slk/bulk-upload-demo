package com.lasath.batch.configuration;

import com.lasath.batch.dao.entity.AccountDetails;
import org.springframework.batch.item.file.transform.FieldSet;

public interface FieldSetMapper<T> extends org.springframework.batch.item.file.mapping.FieldSetMapper<T> {

    @Override
    T mapFieldSet(FieldSet fieldSet);
}
