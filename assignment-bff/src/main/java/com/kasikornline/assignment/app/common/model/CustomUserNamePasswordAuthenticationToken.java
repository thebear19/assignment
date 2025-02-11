package com.kasikornline.assignment.app.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUserNamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    String id;

    public CustomUserNamePasswordAuthenticationToken(Object principal, Object credentials, String id) {
        super(principal, credentials);
        setId(id);
    }

    public CustomUserNamePasswordAuthenticationToken(Object principal, Object credentials, String id, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        setId(id);
    }

}
