package com.project.mwork.Service;

import java.util.List;
import com.project.mwork.DTO.Request.CProjectRequest;
import com.project.mwork.DTO.Response.ProjectResponse;

public interface ProjectService {
	void CreateProject(CProjectRequest request);
	ProjectResponse ReadProject(String id_project);
	List<ProjectResponse> GetListProject(String id_group);
}
