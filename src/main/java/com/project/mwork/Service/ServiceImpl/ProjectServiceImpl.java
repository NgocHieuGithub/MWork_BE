package com.project.mwork.Service.ServiceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.mwork.DTO.Request.CProjectRequest;
import com.project.mwork.DTO.Response.ProjectResponse;
import com.project.mwork.Mapper.DocMapper;
import com.project.mwork.Mapper.ProjectMapper;
import com.project.mwork.Mapper.TaskMapper;
import com.project.mwork.Model.Document;
import com.project.mwork.Model.Project;
import com.project.mwork.Model.Task;
import com.project.mwork.Repository.DocRepository;
import com.project.mwork.Repository.GroupRepository;
import com.project.mwork.Repository.ProjectRepository;
import com.project.mwork.Repository.TaskRepository;
import com.project.mwork.Repository.UserRepository;
import com.project.mwork.Service.ProjectService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Slf4j
public class ProjectServiceImpl implements ProjectService{
	
	ProjectRepository projectRepository;
	GroupRepository groupRepository;
	UserRepository userRepository;
	TaskRepository taskRepository;
	DocRepository docRepository;
	ProjectMapper projectMapper;
	TaskMapper taskMapper;
	DocMapper docMapper;
	
	@Override
	public ProjectResponse CreateProject(CProjectRequest request) {
		Project project = projectMapper.toPoProject(request);
		project.setGroup(groupRepository.findById(request.getId_group()).get());
		ProjectResponse projectResponse = new ProjectResponse();
		if (request.getTaskRequests() != null) {
			List<Task> tasks = request.getTaskRequests().stream().map(taskMapper::toTask).toList();
			for (int i = 0; i < tasks.size(); i++) {
				tasks.get(i).setUser(userRepository.findById(request.getTaskRequests().get(i).getUser_id()).get());
				tasks.get(i).setProject(project);
			}
			projectResponse = projectMapper.toProjectResponse(projectRepository.save(project));
			projectResponse.setTasks((taskRepository.saveAll(tasks).stream().map(taskMapper::toTaskResponse).toList()).stream().collect(Collectors.toSet()));
		}
		else {
			if (request.getDocs() != null) {
				List<Document> documents = request.getDocs().stream().map(docMapper::toDocument).toList();
				for(Document i :documents) {
					i.setProject(project);
				}
				projectResponse = projectMapper.toProjectResponse(projectRepository.save(project));
				projectResponse.setDocs((
						docRepository.
						saveAll(documents).
						stream().
						map(docMapper::toDocResponse).
						toList()).
						stream().
						collect(Collectors.toSet()
								));
			}
			else {
				projectResponse = projectMapper.toProjectResponse(projectRepository.save(project));
			}
		}
		return projectResponse;
	}

	@Override
	public ProjectResponse ReadProject(String id_project) {
		Project project = projectRepository.findById(id_project).orElse(null);
		ProjectResponse projectResponse = projectMapper.toProjectResponse(project);
		projectResponse.setTasks(taskRepository.findByProject(project).stream().map(taskMapper::toTaskResponse).toList().stream().collect(Collectors.toSet()));
		return projectResponse;
	}

	@Override
	public List<Project> GetListProject1(String id_group) {
//		return projectRepository.findByGroup(groupRepository.findById(id_group).get()).stream().map(projectMapper::toProjectResponse).toList();
		List<Project> list = projectRepository.GetProjectFromGroup(id_group);
		System.out.println(list.size());
		return list;
	}

	@Override
	public List<ProjectResponse> GetListProject(String id_group) {
		// TODO Auto-generated method stub
		return null;
	}

}
