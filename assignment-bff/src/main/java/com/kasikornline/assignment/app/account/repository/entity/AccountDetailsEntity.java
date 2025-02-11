package com.kasikornline.assignment.app.account.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ACCOUNT_DETAILS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDetailsEntity {
    @Id
    @Column(name = "ACCOUNT_ID")
    String id;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "IS_MAIN_ACCOUNT")
    BigDecimal isMainAccount;

}
