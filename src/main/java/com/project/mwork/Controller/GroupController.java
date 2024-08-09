package com.project.mwork.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mwork.DTO.ApiResponse;
import com.project.mwork.DTO.Request.CGroupRequest;
import com.project.mwork.DTO.Response.GroupResponse;
import com.project.mwork.DTO.Response.InforGroupResponse;
import com.project.mwork.Service.ServiceImpl.GroupServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("group")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Group")
public class GroupController {
	
	GroupServiceImpl groupImpl;
	@PostMapping("/create")
	public ApiResponse<GroupResponse> CreateGroup(@RequestBody CGroupRequest request){
		return ApiResponse.<GroupResponse>builder()
				.code(1000)
				.result(groupImpl.CreateGroup(request))
				.build();
	}
	
	@GetMapping("/read")
	public ApiResponse<InforGroupResponse> ReadGroup(@RequestParam(value = "id_group") String id_group){
		return ApiResponse.<InforGroupResponse>builder()
				.code(1000)
				.result(groupImpl.ReadGroup(id_group))
				.build();
	}
	
	@PostMapping("/update")
	public ApiResponse<GroupResponse> UpdateGroup(@RequestBody CGroupRequest request){
		return ApiResponse.<GroupResponse>builder()
				.code(1000)
				.result(groupImpl.UpdateGroup(request))
				.build();
	}
	
	@GetMapping("/delete")
	public ApiResponse<Boolean> DeleteGroup(@RequestParam(value = "id_group") String id_group){
		return ApiResponse.<Boolean>builder()
				.code(1000)
				.result(groupImpl.DeleteGroup(id_group))
				.build();
	}
	
	@GetMapping("/getlist")
	public ApiResponse<List<GroupResponse>> GetListGroup(){
		System.out.println("receive data get list group ..................");
		return ApiResponse.<List<GroupResponse>>builder()
				.code(1000)
				.result(groupImpl.GetListGroup())
				.build();
	}
	
	
	
	@GetMapping("/join")
	public ApiResponse<String> JoinGroup(@RequestParam(value = "code_group") String code_group){
		if (groupImpl.JoinGroup(code_group)) {
			return ApiResponse.<String>builder().code(1000).result("Join group successfully").build();
		}
		return ApiResponse.<String>builder().code(1000).result("Join group not successfully").build();
	}
}
