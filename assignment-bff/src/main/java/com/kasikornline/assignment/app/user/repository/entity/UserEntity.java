package com.kasikornline.assignment.app.user.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @Column(name = "USER_ID")
    String id;

    @Column(name = "NAME")
    String name;

}
