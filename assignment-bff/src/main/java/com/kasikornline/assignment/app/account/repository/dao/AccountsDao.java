package com.kasikornline.assignment.app.account.repository.dao;

import com.kasikornline.assignment.app.account.model.Account;

import java.util.List;

public interface AccountsDao {
    List<Account> getAccountsByUserId(String userId);

}
