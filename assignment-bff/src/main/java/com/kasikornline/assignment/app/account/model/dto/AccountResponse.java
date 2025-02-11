package com.kasikornline.assignment.app.account.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String id;
    String number;
    String type;
    String userId;
    BigDecimal balance;
    String currency;
    String issuer;
    boolean isPrimary;
}
