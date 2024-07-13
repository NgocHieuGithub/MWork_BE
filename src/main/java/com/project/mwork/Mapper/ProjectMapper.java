package com.project.mwork.Mapper;

import org.mapstruct.Mapper;

import com.project.mwork.DTO.Request.CProjectRequest;
import com.project.mwork.DTO.Response.ProjectResponse;
import com.project.mwork.Model.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
	Project toPoProject(CProjectRequest request);
	ProjectResponse toProjectResponse(Project request);
}
