package com.project.mwork.Mapper;

import org.mapstruct.Mapper;

import com.project.mwork.DTO.Request.CTaskRequest;
import com.project.mwork.DTO.Request.UTaskRequest;
import com.project.mwork.DTO.Response.TaskResponse;
import com.project.mwork.Model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	Task toTask(CTaskRequest request);
	TaskResponse toTaskResponse(Task request);
	Task toUTask(UTaskRequest request);
}
