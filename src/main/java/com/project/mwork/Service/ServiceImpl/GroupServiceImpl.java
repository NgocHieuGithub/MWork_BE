package com.project.mwork.Service.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.mwork.DTO.Request.CGroupRequest;
import com.project.mwork.DTO.Request.JGroupRequest;
import com.project.mwork.DTO.Response.GroupResponse;
import com.project.mwork.DTO.Response.InforGroupResponse;
import com.project.mwork.DTO.Response.UserResponse;
import com.project.mwork.Mapper.GroupMapper;
import com.project.mwork.Model.Group;
import com.project.mwork.Model.Group_details;
import com.project.mwork.Model.User;
import com.project.mwork.Repository.GroupDetailsRepository;
import com.project.mwork.Repository.GroupRepository;
import com.project.mwork.Repository.UserRepository;
import com.project.mwork.Service.GroupService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GroupServiceImpl implements GroupService{
	
	GroupMapper groupMapper;
	GroupRepository groupRepository;
	UserRepository userRepository;
	GroupDetailsRepository groupDetailsRepository;
	
	@Override
	public GroupResponse CreateGroup(CGroupRequest request) {
		Group group = groupMapper.toGroup(request);
		group.setLeader(request.getLeader());
		group = groupRepository.save(group);
		Group_details group_details = Group_details
				.builder()
				.isJoin(true)
				.group(group)
				.user(request.getLeader())
				.build();
		groupDetailsRepository.save(group_details);
		return groupMapper.toGroupResponse(group);
	}

	@Override
	public InforGroupResponse ReadGroup(String id_group) {
		List<UserResponse> uList = userRepository.GetListUserInGroup(id_group);
		log.info("count user in group: " + uList.size() + "");
		InforGroupResponse inforGroupResponse = InforGroupResponse
				.builder()
				.inforGroup(groupMapper.toGroupResponse(groupRepository.findById(id_group).orElseThrow()))
				.inforListMember(uList)
				.build();
		return inforGroupResponse;
	}

	@Override
	public GroupResponse UpdateGroup(CGroupRequest request) {
		return groupMapper.toGroupResponse(groupRepository.save(groupMapper.toGroup(request)));
	}

	@Override
	public Boolean DeleteGroup(String id_group) {
		try {
			groupRepository.delete(groupRepository.findById(id_group).orElseThrow());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<GroupResponse> GetListGroup() {
		String id_user = SecurityContextHolder.getContext().getAuthentication().getName();
		var jwwt = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("id_user: "  + jwwt.getCredentials() + " \n "+ jwwt.getDetails() +"\n "+ jwwt.getPrincipal() +"\n "+ jwwt.getName());
		//User user = userRepository.findById(id_user).orElse(null);
		//return groupRepository.findAll().stream().map(groupMapper::toGroupResponse).toList();
		return groupRepository.GetListGroupByUser(id_user);
	}

	@Override
	public Boolean JoinGroup(JGroupRequest request) {
		User user = userRepository.findById(request.getUser_id()).get();
		Group group = groupRepository.findByCodeGroup(request.getCode_group());
		if (groupDetailsRepository.existsByUserAndGroup(user, group)) {
			return false;
		}
		Group_details group_details = Group_details
				.builder()
				.isJoin(false)
				.user(user)
				.group(group)
				.build();
		try {
			groupDetailsRepository.save(group_details);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
