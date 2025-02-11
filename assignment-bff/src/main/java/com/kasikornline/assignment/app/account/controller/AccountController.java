package com.kasikornline.assignment.app.account.controller;

import com.kasikornline.assignment.app.account.api.AccountApi;
import com.kasikornline.assignment.app.account.mapper.AccountsMapper;
import com.kasikornline.assignment.app.account.model.dto.AccountRequest;
import com.kasikornline.assignment.app.account.model.dto.AccountResponse;
import com.kasikornline.assignment.app.account.service.AccountsService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountController implements AccountApi {

    final AccountsService accountsService;
    final AccountsMapper accountsMapper;

    public List<AccountResponse> getAccountsById(@Valid @RequestBody AccountRequest request) {
        log.info("START : get account info for ID:{}", request.getUserId());
        return accountsMapper.toResp(accountsService.getAccountById(request.getUserId()));
    }

}
