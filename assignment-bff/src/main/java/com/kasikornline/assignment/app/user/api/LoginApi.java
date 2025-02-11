package com.kasikornline.assignment.app.user.api;

import com.kasikornline.assignment.app.user.model.dto.UserRequest;
import com.kasikornline.assignment.app.user.model.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/login")
public interface LoginApi {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    UserResponse login(UserRequest req);

}
