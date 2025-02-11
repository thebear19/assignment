package com.kasikornline.assignment.app.account.service.impl;

import com.kasikornline.assignment.app.account.model.Account;
import com.kasikornline.assignment.app.account.repository.dao.AccountsDao;
import com.kasikornline.assignment.app.account.service.AccountsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountsServiceImpl implements AccountsService {

    final AccountsDao accountsDao;

    @Override
    public List<Account> getAccountById(String userId) {
        return accountsDao.getAccountsByUserId(userId);
    }
}
