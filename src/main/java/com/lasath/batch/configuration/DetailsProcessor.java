package com.lasath.batch.configuration;

import com.lasath.batch.dao.entity.AccountDetails;
import org.springframework.batch.item.ItemProcessor;

public interface DetailsProcessor<T> extends ItemProcessor<T, T> {

    @Override
    T process(T t);
}
