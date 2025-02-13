package com.kasikornline.assignment.app.common.security;

import com.kasikornline.assignment.app.common.model.CustomUserNamePasswordAuthenticationToken;
import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasicAuthenticationProviderTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private BasicAuthenticationProvider authenticationProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticate_Success() {
        String username = "testUser";
        String password = "123456";
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setId("userId");

        when(userService.getUserByName(username)).thenReturn(userInfo);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authenticationProvider.authenticate(authentication);

        assertNotNull(result);
        assertInstanceOf(CustomUserNamePasswordAuthenticationToken.class, result);
        assertEquals(username, result.getName());
        verify(userService, times(1)).getUserByName(username);
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        String username = "testUser";
        String password = "wrongPassword";

        when(userService.getUserByName(username)).thenReturn(null);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(authentication);
        });

        verify(userService, times(1)).getUserByName(username);
    }

    @Test
    public void testAuthenticate_UserNotFound() {
        String username = "testUser";
        String password = "123456";

        when(userService.getUserByName(username)).thenReturn(null);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(authentication);
        });

        verify(userService, times(1)).getUserByName(username);
    }

    @Test
    public void testSupports_ValidClass() {
        assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testSupports_InvalidClass() {
        assertFalse(authenticationProvider.supports(CustomUserNamePasswordAuthenticationToken.class));
    }
}
