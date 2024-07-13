package com.project.mwork.Mapper;

import org.mapstruct.Mapper;

import com.project.mwork.DTO.Request.CGroupRequest;
import com.project.mwork.DTO.Response.GroupResponse;
import com.project.mwork.Model.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
	Group toGroup(CGroupRequest request);
	GroupResponse toGroupResponse(Group group);
}
