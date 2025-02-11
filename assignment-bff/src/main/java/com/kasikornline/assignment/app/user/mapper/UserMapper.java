package com.kasikornline.assignment.app.user.mapper;

import com.kasikornline.assignment.app.user.model.UserInfo;
import com.kasikornline.assignment.app.user.model.dto.UserResponse;
import com.kasikornline.assignment.app.user.repository.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserResponse toResp(UserInfo user);

    public abstract UserInfo fromEntity(UserEntity entity);

}

