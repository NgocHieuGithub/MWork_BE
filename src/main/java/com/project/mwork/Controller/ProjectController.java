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

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Project")
public class ProjectController {
	ProjectServiceImpl projectServiceImpl;
	@PostMapping("/create")
	public ApiResponse<String> CreateProject(@RequestBody CProjectRequest request){
		return ApiResponse.<String>builder().code(1000).result("Success").build();
	}
	@GetMapping("/read")
	public ApiResponse<ProjectResponse> ReadProject(@RequestParam(value = "id_project") String id_project){
		return ApiResponse.<ProjectResponse>builder().code(1000).result(projectServiceImpl.ReadProject(id_project)).build();
	}
	@GetMapping("/getlist")
	public ApiResponse<List<ProjectResponse>> GetListProject1(@RequestParam(value = "id_group") String id_group){
		return ApiResponse.<List<ProjectResponse>>builder().code(1000).result(projectServiceImpl.GetListProject(id_group)).build();
	}
}
