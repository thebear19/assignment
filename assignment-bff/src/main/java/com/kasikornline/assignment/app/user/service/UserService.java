package com.kasikornline.assignment.app.user.service;

import com.kasikornline.assignment.app.user.model.UserInfo;

public interface UserService {
    UserInfo getUserByName(String name);
}
