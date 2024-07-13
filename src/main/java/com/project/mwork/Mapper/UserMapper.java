package com.project.mwork.Mapper;

import org.mapstruct.Mapper;

import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Response.UserResponse;
import com.project.mwork.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(CUserRequest request);
	UserResponse toUserResponse(User user);
}
