package com.kasikornline.assignment.app.account.service;

import com.kasikornline.assignment.app.account.model.Account;

import java.util.List;

public interface AccountsService {
    List<Account> getAccountById(String userId);
}
