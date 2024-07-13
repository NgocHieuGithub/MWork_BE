package com.project.mwork.Service;

import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Request.SignInRequest;
import com.project.mwork.DTO.Response.TokenResponse;

public interface AuthenticationService {
	public TokenResponse SignIn(SignInRequest request);
	public TokenResponse SignUp(CUserRequest request);
	public void LogOut();
}
