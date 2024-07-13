package com.project.mwork.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mwork.DTO.ApiResponse;
import com.project.mwork.DTO.Request.CProjectRequest;
import com.project.mwork.DTO.Response.ProjectResponse;
import com.project.mwork.Service.ServiceImpl.ProjectServiceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectController {
	
	ProjectServiceImpl projectServiceImpl;
	@PostMapping("/create")
	public ApiResponse<ProjectResponse> CreateProject(@RequestBody CProjectRequest request){
		return ApiResponse.<ProjectResponse>builder().code(1000).result(projectServiceImpl.CreateProject(request)).build();
	}
	@GetMapping("/read")
	public ApiResponse<ProjectResponse> ReadProject(@RequestParam(value = "id_project") String id_project){
		return ApiResponse.<ProjectResponse>builder().code(1000).result(projectServiceImpl.ReadProject(id_project)).build();
	}
	@GetMapping("/getlist")
	public ApiResponse<List<ProjectResponse>> GetListProject(@RequestParam(value = "id_group") String id_group){
		return ApiResponse.<List<ProjectResponse>>builder().code(1000).result(projectServiceImpl.GetListProject(id_group)).build();
	}
}
