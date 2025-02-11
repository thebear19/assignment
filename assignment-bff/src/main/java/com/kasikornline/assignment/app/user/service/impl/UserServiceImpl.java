package com.kasikornline.assignment.app.user.service.impl;

import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.repository.dao.UserDao;
import com.kasikornline.assignment.app.user.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserDao userDao;

    @Override
    public UserInfo getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
