package com.kasikornline.assignment.app.account.repository.dao.impl;

import com.kasikornline.assignment.app.account.mapper.AccountsMapper;
import com.kasikornline.assignment.app.account.model.Account;
import com.kasikornline.assignment.app.account.repository.AccountBalancesRepository;
import com.kasikornline.assignment.app.account.repository.AccountDetailsRepository;
import com.kasikornline.assignment.app.account.repository.AccountsRepository;
import com.kasikornline.assignment.app.account.repository.entity.AccountDetailsEntity;
import com.kasikornline.assignment.app.account.repository.entity.AccountsEntity;
import com.kasikornline.assignment.app.exception.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountsDaoImplTest {

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private AccountBalancesRepository accountBalancesRepository;

    @Mock
    private AccountDetailsRepository accountDetailsRepository;

    @Mock
    private AccountsMapper accountsMapper;

    @InjectMocks
    private AccountsDaoImpl accountsDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountsByUserId_Success() {
        String userId = "testUser";
        Account account = new Account();
        account.setId("testAccount");
        AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setIsMainAccount(BigDecimal.ONE);
        List<AccountsEntity> entities = Collections.singletonList(new AccountsEntity());
        List<Account> accounts = Collections.singletonList(account);

        when(accountDetailsRepository.findById(anyString())).thenReturn(Optional.of(accountDetailsEntity));
        when(accountsRepository.findByUserId(userId)).thenReturn(Optional.of(entities));
        when(accountsMapper.fromEntities(entities)).thenReturn(accounts);

        List<Account> result = accountsDao.getAccountsByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(accountsRepository, times(1)).findByUserId(userId);
        verify(accountsMapper, times(1)).fromEntities(entities);
    }

    @Test
    public void testGetAccountsByUserId_AccountNotFound() {
        String userId = "testUser";

        when(accountsRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountsDao.getAccountsByUserId(userId));

        verify(accountsRepository, times(1)).findByUserId(userId);
        verify(accountsMapper, never()).fromEntities(anyList());
    }
}
