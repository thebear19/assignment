package com.kasikornline.assignment.app.user.controller;

import com.kasikornline.assignment.app.common.model.CustomUserNamePasswordAuthenticationToken;
import com.kasikornline.assignment.app.user.api.LoginApi;
import com.kasikornline.assignment.app.user.model.dto.UserRequest;
import com.kasikornline.assignment.app.user.model.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginController implements LoginApi {
    static final String DUMMY_TOKEN = "token";
    final AuthenticationManager authenticationManager;

    @Override
    public UserResponse login(@Valid @RequestBody UserRequest req) {
        log.info("START : logging in username: {}", req.getName());
        CustomUserNamePasswordAuthenticationToken authentication = getAuthenticationToken(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            log.info("END : logging in username: {}", req.getName());

            return UserResponse.builder()
                    .id(authentication.getId())
                    .name(authentication.getName())
                    .token(DUMMY_TOKEN)
                    .build();
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    CustomUserNamePasswordAuthenticationToken getAuthenticationToken(UserRequest req) {
        CustomUserNamePasswordAuthenticationToken authentication;

        try {
            authentication = (CustomUserNamePasswordAuthenticationToken) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getName(), req.getPassword())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return authentication;
    }
}
