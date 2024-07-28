package com.project.mwork.Service;

import java.util.List;

import com.project.mwork.DTO.Request.CProjectRequest;
import com.project.mwork.DTO.Response.ProjectResponse;
import com.project.mwork.Model.Project;

public interface ProjectService {
	ProjectResponse CreateProject(CProjectRequest request);
	ProjectResponse ReadProject(String id_project);
	List<ProjectResponse> GetListProject(String id_group);
	List<Project> GetListProject1(String id_group);
}
