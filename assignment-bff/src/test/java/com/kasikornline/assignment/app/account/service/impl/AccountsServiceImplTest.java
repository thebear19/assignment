package com.kasikornline.assignment.app.account.service.impl;

import com.kasikornline.assignment.app.account.model.Account;
import com.kasikornline.assignment.app.account.repository.dao.AccountsDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AccountsServiceImplTest {

    @Mock
    private AccountsDao accountsDao;

    @InjectMocks
    private AccountsServiceImpl accountsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountById_Success() {
        String userId = "testUser";
        List<Account> expectedAccounts = Collections.singletonList(new Account());

        when(accountsDao.getAccountsByUserId(userId)).thenReturn(expectedAccounts);

        List<Account> result = accountsService.getAccountById(userId);

        assertNotNull(result);
        assertEquals(expectedAccounts.size(), result.size());
        verify(accountsDao, times(1)).getAccountsByUserId(userId);
    }

    @Test
    public void testGetAccountById_NoAccounts() {
        String userId = "testUser";
        List<Account> expectedAccounts = Collections.emptyList();

        when(accountsDao.getAccountsByUserId(userId)).thenReturn(expectedAccounts);

        List<Account> result = accountsService.getAccountById(userId);

        assertNotNull(result);
        assertEquals(expectedAccounts.size(), result.size());
        verify(accountsDao, times(1)).getAccountsByUserId(userId);
    }
}
