package com.kasikornline.assignment.app.user.repository.dao;

import com.kasikornline.assignment.app.user.model.UserInfo;


public interface UserDao {
    UserInfo getUserByName(String name);

}
