package com.kasikornline.assignment.app.user.repository.dao.impl;

import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.mapper.UserMapper;
import com.kasikornline.assignment.app.user.repository.UserRepository;
import com.kasikornline.assignment.app.user.repository.dao.UserDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDaoImpl implements UserDao {

    final UserRepository userRepository;
    final UserMapper userMapper;

    @Override
    public UserInfo getUserByName(String name) {
        return userMapper.fromEntity(userRepository.findByName(name).orElse(null));
    }
}
