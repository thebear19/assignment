package com.kasikornline.assignment.app.account.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    String id;
    String number;
    String type;
    String userId;
    BigDecimal balance;
    String currency;
    String issuer;
    boolean isPrimary;
}
