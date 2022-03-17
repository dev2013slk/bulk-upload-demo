package com.lasath.batch.dao.repository;

import com.lasath.batch.dao.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

}
