package com.project.mwork.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.project.mwork.DTO.Request.CProjectRequest;
import com.project.mwork.DTO.Response.DocResponse;
import com.project.mwork.DTO.Response.ProjectResponse;
import com.project.mwork.DTO.Response.UserResponse;
import com.project.mwork.Model.Post;

@Mapper(componentModel = "spring", uses = DocMapper.class)
public interface ProjectMapper {
	Post toPoProject(CProjectRequest request);
	ProjectResponse toProjectResponse(Post request);
	default ProjectResponse tProjectResponse(Post req) {
		UserResponse userResponse = UserResponse.builder()
				.username(req.getOwner().getUsername())
				.urlavt(req.getOwner().getUrlavt())
				.build();
		ProjectResponse res = ProjectResponse.builder()
				.id(req.getId())
				.name(req.getName())
				.dateCreate(req.getDateCreate())
				.type(req.getType())
				.owner(userResponse)
				.build();
		List<DocResponse> docs = new ArrayList<DocResponse>();
		System.out.println("size" + req.getDocs().size());
		if (!req.getDocs().isEmpty()) {
			System.out.println(req.getDocs().size());
			for (int i = 0; i < req.getDocs().size(); i++) {
				docs.add(Mappers.getMapper(DocMapper.class).toDocResponse(req.getDocs().get(i)));
			}
			res.setDocs(docs.stream().collect(Collectors.toSet()));
		}
		
		return res;
	}
}
