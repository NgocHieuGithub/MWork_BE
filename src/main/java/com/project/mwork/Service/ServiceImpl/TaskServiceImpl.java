package com.project.mwork.Service.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.mwork.DTO.Request.FTaskRequest;
import com.project.mwork.DTO.Request.UTaskRequest;
import com.project.mwork.DTO.Response.TaskResponse;
import com.project.mwork.Mapper.TaskMapper;
import com.project.mwork.Model.Document;
import com.project.mwork.Model.Task;
import com.project.mwork.Repository.DocRepository;
import com.project.mwork.Repository.TaskRepository;
import com.project.mwork.Repository.UserRepository;
import com.project.mwork.Repository.HttpClient.GoogleSheetClient;
import com.project.mwork.Service.TaskService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService{
	DocRepository docRepository;
	TaskRepository taskRepository;
	UserRepository userRepository;
	TaskMapper taskMapper;
	GoogleSheetClient googleSheetClient;
	
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

	@Override
	public List<TaskResponse> GetListTask(String id_group) {
		String id_userString = SecurityContextHolder.getContext().getAuthentication().getName();
		return taskRepository.GetListTask(id_group, id_userString);
	}

	@Override
	public Boolean UpdateTask(FTaskRequest req) {
		Task task = taskRepository.findById(req.getId()).orElse(null);
		task.setFinishAt(req.getFinishAt());
		Document document = Document
				.builder()
				.task(task)
				.name(req.getName())
				.url(req.getUrlfile())
				.build();
		Set<Document> set = new HashSet<Document>();
		set.add(document);
		try {
			docRepository.save(document);
			taskRepository.save(task);
			return googleSheetClient.UpdateGoogleSheetClient(req).getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
