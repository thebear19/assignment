package com.kasikornline.assignment.app.user.service.impl;

import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.repository.dao.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByName_Success() {
        String username = "testUser";
        UserInfo expectedUserInfo = new UserInfo();
        expectedUserInfo.setName(username);
        expectedUserInfo.setId("userId");

        when(userDao.getUserByName(username)).thenReturn(expectedUserInfo);

        UserInfo result = userService.getUserByName(username);

        assertNotNull(result);
        assertEquals(expectedUserInfo.getName(), result.getName());
        assertEquals(expectedUserInfo.getId(), result.getId());
        verify(userDao, times(1)).getUserByName(username);
    }

    @Test
    public void testGetUserByName_UserNotFound() {
        String username = "nonExistentUser";

        when(userDao.getUserByName(username)).thenReturn(null);

        UserInfo result = userService.getUserByName(username);

        assertNull(result);
        verify(userDao, times(1)).getUserByName(username);
    }
}
