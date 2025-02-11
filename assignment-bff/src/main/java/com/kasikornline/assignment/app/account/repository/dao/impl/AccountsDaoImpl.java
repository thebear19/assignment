package com.kasikornline.assignment.app.account.repository.dao.impl;

import com.kasikornline.assignment.app.account.mapper.AccountsMapper;
import com.kasikornline.assignment.app.account.model.Account;
import com.kasikornline.assignment.app.account.repository.AccountBalancesRepository;
import com.kasikornline.assignment.app.account.repository.AccountDetailsRepository;
import com.kasikornline.assignment.app.account.repository.AccountsRepository;
import com.kasikornline.assignment.app.account.repository.dao.AccountsDao;
import com.kasikornline.assignment.app.account.repository.entity.AccountsEntity;
import com.kasikornline.assignment.app.exception.AccountNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountsDaoImpl implements AccountsDao {

    final AccountsRepository accountsRepository;
    final AccountBalancesRepository accountBalancesRepository;
    final AccountDetailsRepository accountDetailsRepository;
    final AccountsMapper accountsMapper;

    @Override
    public List<Account> getAccountsByUserId(String userId) {
        List<Account> accounts = getAccounts(userId);
        setAccountsDetails(accounts);

        return accounts;
    }

    List<Account> getAccounts(String userId) {
        List<AccountsEntity> entities = accountsRepository.findByUserId(userId)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> {
                    log.error("Account not found for user: {}", userId);
                    return new AccountNotFoundException("Account not found, please check again.");
                });

        return accountsMapper.fromEntities(entities);
    }

    void setAccountsDetails(List<Account> accounts) {
        accounts.forEach(account -> {
            log.info("Setting Account details for: {}", account.getId());
            setBalance(account);
            setPrimaryFlag(account);
        });
    }

    void setPrimaryFlag(Account account) {
        accountDetailsRepository.findById(account.getId())
                .ifPresent(accountDetails ->
                        account.setPrimary(BigDecimal.ONE.equals(accountDetails.getIsMainAccount())));
    }

    void setBalance(Account account) {
        accountBalancesRepository.findById(account.getId())
                .ifPresent(accountBalance -> account.setBalance(accountBalance.getAmount()));
    }
}
