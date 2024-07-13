package com.project.mwork.Service;

import java.util.List;

import com.project.mwork.DTO.Request.CGroupRequest;
import com.project.mwork.DTO.Request.JGroupRequest;
import com.project.mwork.DTO.Response.GroupResponse;
import com.project.mwork.DTO.Response.InforGroupResponse;

public interface GroupService {
	GroupResponse CreateGroup(CGroupRequest request);
	InforGroupResponse ReadGroup(String id_group);
	GroupResponse UpdateGroup(CGroupRequest request);
	Boolean DeleteGroup(String id_group);
	List<GroupResponse> GetListGroup();
	Boolean JoinGroup(JGroupRequest jGroupRequest);
}
