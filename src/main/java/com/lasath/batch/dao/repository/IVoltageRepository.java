package com.lasath.batch.dao.repository;

import com.lasath.batch.dao.entity.Voltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoltageRepository extends JpaRepository<Voltage, Long> {

}
