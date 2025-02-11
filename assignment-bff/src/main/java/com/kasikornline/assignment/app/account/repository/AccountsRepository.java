package com.kasikornline.assignment.app.account.repository;

import com.kasikornline.assignment.app.account.repository.entity.AccountsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<AccountsEntity, String> {
    Optional<List<AccountsEntity>> findByUserId(String id);
}
