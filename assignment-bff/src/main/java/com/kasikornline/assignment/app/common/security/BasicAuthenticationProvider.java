package com.kasikornline.assignment.app.common.security;

import com.kasikornline.assignment.app.common.model.CustomUser;
import com.kasikornline.assignment.app.common.model.CustomUserNamePasswordAuthenticationToken;
import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasicAuthenticationProvider implements AuthenticationProvider {
    static final String DUMMY_CREDENTIAL = "123456";

    final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        log.info("authenticate {}:{}", authentication.getName(), authentication.getDetails());

        CustomUser customUser = authenticateWithPassword(authentication);

        return new CustomUserNamePasswordAuthenticationToken(customUser.getUsername(), customUser.getPassword(),
                customUser.getId(), customUser.getAuthorities());
    }

    CustomUser authenticateWithPassword(Authentication authentication) {
        log.info("Checking username in database");
        UserInfo user = userService.getUserByName(authentication.getName());

        log.info("Checking username and password");
        if (user == null || !DUMMY_CREDENTIAL.equals(authentication.getCredentials())) {
            throw new BadCredentialsException("User or Password invalid");
        }

        log.info("Built user details");
        return new CustomUser(user.getName(), DUMMY_CREDENTIAL, user.getId(), List.of(new SimpleGrantedAuthority("USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
