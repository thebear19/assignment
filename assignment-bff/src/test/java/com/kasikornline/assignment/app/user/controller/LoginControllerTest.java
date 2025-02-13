package com.kasikornline.assignment.app.user.controller;

import com.kasikornline.assignment.app.common.model.CustomUserNamePasswordAuthenticationToken;
import com.kasikornline.assignment.app.user.model.dto.UserRequest;
import com.kasikornline.assignment.app.user.model.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testLogin_Success() {
        String username = "User_000018b0e1a211ef95a30242ac180002";
        String password = "123456";
        String userId = "000018b0e1a211ef95a30242ac180002";

        UserRequest userRequest = new UserRequest();
        userRequest.setName(username);
        userRequest.setPassword(password);

        CustomUserNamePasswordAuthenticationToken authentication =
                new CustomUserNamePasswordAuthenticationToken(
                        username,
                        password,
                        userId,
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                );

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        UserResponse response = loginController.login(userRequest);

        assertNotNull(response);
        assertEquals(username, response.getName());
        assertEquals(userId, response.getId());
        assertEquals("token", response.getToken());
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testLogin_InvalidPassword() {
        String username = "User_000018b0e1a211ef95a30242ac180002";
        String password = "12345";

        UserRequest userRequest = new UserRequest();
        userRequest.setName(username);
        userRequest.setPassword(password);

        assertThrows(BadCredentialsException.class, () -> {
            loginController.login(userRequest);
        });
    }

    @Test
    public void testLogin_InvalidCredentials() {
        String username = "testUser";
        String password = "wrongPassword";

        UserRequest userRequest = new UserRequest();
        userRequest.setName(username);
        userRequest.setPassword(password);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> {
            loginController.login(userRequest);
        });

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
