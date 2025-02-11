package com.kasikornline.assignment.app.account.repository;

import com.kasikornline.assignment.app.account.repository.entity.AccountDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetailsEntity, String> {
}
