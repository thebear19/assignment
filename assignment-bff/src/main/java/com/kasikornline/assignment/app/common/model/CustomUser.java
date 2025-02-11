package com.kasikornline.assignment.app.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUser extends User {

    String id;

    public CustomUser(String username, String password, String id, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        setId(id);
    }
}
