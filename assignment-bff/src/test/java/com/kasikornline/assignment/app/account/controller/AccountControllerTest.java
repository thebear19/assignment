package com.kasikornline.assignment.app.account.controller;

import com.kasikornline.assignment.app.account.mapper.AccountsMapper;
import com.kasikornline.assignment.app.account.model.Account;
import com.kasikornline.assignment.app.account.model.dto.AccountRequest;
import com.kasikornline.assignment.app.account.model.dto.AccountResponse;
import com.kasikornline.assignment.app.account.service.AccountsService;
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

public class AccountControllerTest {

    @Mock
    private AccountsService accountsService;

    @Mock
    private AccountsMapper accountsMapper;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountsById_Success() {
        String userId = "testUser";
        AccountRequest request = new AccountRequest();
        request.setUserId(userId);

        List<Account> accountList = Collections.singletonList(new Account());
        List<AccountResponse> expectedResponse = Collections.singletonList(AccountResponse.builder().build());

        when(accountsService.getAccountById(userId)).thenReturn(accountList);
        when(accountsMapper.toResp(accountList)).thenReturn(expectedResponse);

        List<AccountResponse> result = accountController.getAccountsById(request);

        assertNotNull(result);
        assertEquals(expectedResponse.size(), result.size());
        verify(accountsService, times(1)).getAccountById(userId);
        verify(accountsMapper, times(1)).toResp(accountList);
    }

    @Test
    public void testGetAccountsById_NoAccounts() {
        String userId = "testUser";
        AccountRequest request = new AccountRequest();
        request.setUserId(userId);

        List<Account> accountList = Collections.emptyList();
        List<AccountResponse> expectedResponse = Collections.emptyList();

        when(accountsService.getAccountById(userId)).thenReturn(accountList);
        when(accountsMapper.toResp(accountList)).thenReturn(expectedResponse);

        List<AccountResponse> result = accountController.getAccountsById(request);

        assertNotNull(result);
        assertEquals(expectedResponse.size(), result.size());
        verify(accountsService, times(1)).getAccountById(userId);
        verify(accountsMapper, times(1)).toResp(accountList);
    }
}
