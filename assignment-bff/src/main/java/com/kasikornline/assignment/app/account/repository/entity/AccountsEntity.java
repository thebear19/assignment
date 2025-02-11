package com.kasikornline.assignment.app.account.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "ACCOUNTS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountsEntity {
    @Id
    @Column(name = "ACCOUNT_ID")
    String id;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "TYPE")
    String type;

    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "ACCOUNT_NUMBER")
    String accountNumber;

    @Column(name = "ISSUER")
    String issuer;

}
