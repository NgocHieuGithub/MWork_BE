package com.project.mwork.Service;

import java.util.List;

import com.project.mwork.DTO.Request.UTaskRequest;
import com.project.mwork.DTO.Response.TaskResponse;

public interface TaskService {
	boolean DeleteTask(String id_task);
	TaskResponse UpdateResponse(UTaskRequest request);
	List<TaskResponse> GetListTask(String id_group);
}
