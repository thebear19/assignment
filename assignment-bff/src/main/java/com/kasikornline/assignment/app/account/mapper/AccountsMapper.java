package com.kasikornline.assignment.app.account.mapper;

import com.kasikornline.assignment.app.account.model.Account;
import com.kasikornline.assignment.app.account.model.dto.AccountResponse;
import com.kasikornline.assignment.app.account.repository.entity.AccountsEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AccountsMapper {

    public abstract AccountResponse toResp(Account account);

    public abstract List<AccountResponse> toResp(List<Account> account);

    @Mapping(target = "number", source = "accountNumber")
    public abstract Account fromEntity(AccountsEntity entity);

    public abstract List<Account> fromEntities(List<AccountsEntity> entities);

    @AfterMapping
    protected void afterToResp(@MappingTarget AccountResponse response, Account account) {
        response.setPrimary(account.isPrimary());
    }

}

