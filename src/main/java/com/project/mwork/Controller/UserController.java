package com.project.mwork.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mwork.DTO.ApiResponse;
import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Response.UserResponse;
import com.project.mwork.Service.ServiceImpl.UserServiceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/create")
	public ApiResponse<UserResponse> CreateUser(@RequestBody CUserRequest request) {
		System.out.println("Data receive:" + request.toString());
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userServiceImpl.CreateUser(request))
				.build();
	}
	
	@GetMapping("/read")
	public ApiResponse<UserResponse> ReadUser(@RequestParam(value = "id_user") String id_user){
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userServiceImpl.ReadUser(id_user))
				.build();
	}
	
	@PostMapping("/update")
	public ApiResponse<UserResponse> UpdateUser(@RequestBody CUserRequest request){
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userServiceImpl.UpdateUser(request))
				.build();
	}
	
	@GetMapping("/delete")
	public ApiResponse<Boolean> DeleteUser(@RequestParam(value = "id_user") String id_user){
		return ApiResponse.<Boolean>builder()
				.code(1000)
				.result(userServiceImpl.DeleteUser(id_user))
				.build();
	}
	
	@GetMapping("/getlist")
	public ApiResponse<List<UserResponse>> GetListUser(){
		return ApiResponse.<List<UserResponse>>builder()
				.code(1000)
				.result(userServiceImpl.getList())
				.build();
	}
	
}
