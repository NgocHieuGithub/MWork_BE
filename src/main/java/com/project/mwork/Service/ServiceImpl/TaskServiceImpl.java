package com.project.mwork.Service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.project.mwork.DTO.Request.UTaskRequest;
import com.project.mwork.DTO.Response.TaskResponse;
import com.project.mwork.Mapper.TaskMapper;
import com.project.mwork.Model.Task;
import com.project.mwork.Repository.TaskRepository;
import com.project.mwork.Repository.UserRepository;
import com.project.mwork.Service.TaskService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService{

	TaskRepository taskRepository;
	UserRepository userRepository;
	TaskMapper taskMapper;
	
	@Override
	public boolean DeleteTask(String id_task) {
		try {
			taskRepository.deleteById(id_task);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public TaskResponse UpdateResponse(UTaskRequest request) {
		Task task = taskRepository.findById(request.getId()).orElse(null);
		task.setDeadLine(request.getDeadLine());
		task.setNameTask(request.getNameTask());
		task.setUser(userRepository.findById(request.getUser_id()).get());
		return taskMapper.toTaskResponse(taskRepository.save(task));
	}

}
