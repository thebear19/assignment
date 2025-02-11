package com.kasikornline.assignment.app.account.api;

import com.kasikornline.assignment.app.account.model.dto.AccountRequest;
import com.kasikornline.assignment.app.account.model.dto.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api/v1/account")
public interface AccountApi {

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    List<AccountResponse> getAccountsById(AccountRequest request);

}
