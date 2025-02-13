package com.kasikornline.assignment.app.user.repository.dao.impl;

import com.kasikornline.assignment.app.user.mapper.UserMapper;
import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.repository.UserRepository;
import com.kasikornline.assignment.app.user.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserDaoImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserDaoImpl userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByName_Success() {
        String username = "testUser";
        UserEntity userEntity = new UserEntity();
        UserInfo expectedUserInfo = new UserInfo();
        expectedUserInfo.setName(username);

        when(userRepository.findByName(username)).thenReturn(Optional.of(userEntity));
        when(userMapper.fromEntity(userEntity)).thenReturn(expectedUserInfo);

        UserInfo result = userDao.getUserByName(username);

        assertEquals(expectedUserInfo, result);
        verify(userRepository, times(1)).findByName(username);
        verify(userMapper, times(1)).fromEntity(userEntity);
    }

    @Test
    public void testGetUserByName_UserNotFound() {
        String username = "nonExistentUser";

        when(userRepository.findByName(username)).thenReturn(Optional.empty());

        UserInfo result = userDao.getUserByName(username);

        assertNull(result);
        verify(userRepository, times(1)).findByName(username);
    }
}
