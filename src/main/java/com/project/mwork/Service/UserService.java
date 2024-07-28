package com.project.mwork.Service;

import java.util.List;

import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Response.UserResponse;

public interface UserService {
	UserResponse CreateUser(CUserRequest request);
	UserResponse ReadUser(String id_user);
	UserResponse UpdateUser(CUserRequest request);
	Boolean DeleteUser(String id_user);
	List<UserResponse> getList();
	List<UserResponse> menUserResponses(String id_group);
}
