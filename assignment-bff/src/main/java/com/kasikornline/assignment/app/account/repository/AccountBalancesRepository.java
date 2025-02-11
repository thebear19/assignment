package com.kasikornline.assignment.app.account.repository;

import com.kasikornline.assignment.app.account.repository.entity.AccountBalancesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalancesRepository extends CrudRepository<AccountBalancesEntity, String> {
}
