package com.project.mwork.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mwork.DTO.ApiResponse;
import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Request.SignInRequest;
import com.project.mwork.DTO.Response.TokenResponse;
import com.project.mwork.Service.ServiceImpl.AuthenticationServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authenticate")
public class AuthenticationController {
	
	AuthenticationServiceImpl authenticationServiceImpl;
	
	@PostMapping("/SignIn")
	public ApiResponse<TokenResponse> SignIn(@RequestBody SignInRequest request) {
		return ApiResponse.<TokenResponse>builder().code(1000).result(authenticationServiceImpl.SignIn(request)).build();
	}
	
	@PostMapping("/SignUp")
	public ApiResponse<TokenResponse> SignUp(@RequestBody CUserRequest userRequest){
		return ApiResponse.<TokenResponse>builder().code(1000).result(authenticationServiceImpl.SignUp(userRequest)).build();
	}
	
}
