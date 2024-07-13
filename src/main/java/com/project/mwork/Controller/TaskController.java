package com.project.mwork.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mwork.DTO.ApiResponse;
import com.project.mwork.DTO.Request.UTaskRequest;
import com.project.mwork.DTO.Response.TaskResponse;
import com.project.mwork.Service.ServiceImpl.TaskServiceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {
	
	TaskServiceImpl taskServiceImpl;
	
	@GetMapping("delete")
	public ApiResponse<String> DeleteTask(@RequestParam(value = "id_task") String id_task){
		boolean check = taskServiceImpl.DeleteTask(id_task);
		if (!check) {
			return ApiResponse.<String>builder().code(1000).result("Delete not successfully").build(); 
		}
		return ApiResponse.<String>builder().code(1000).result("Delete successfully").build();
	}
	
	@PostMapping("update")
	public ApiResponse<TaskResponse> UpdateTask(@RequestBody UTaskRequest request) {
		return ApiResponse.<TaskResponse>builder().code(1000).result(taskServiceImpl.UpdateResponse(request)).build();
	}
	
}
