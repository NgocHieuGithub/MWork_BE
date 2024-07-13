package com.project.mwork.Service.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.mwork.DTO.Request.CUserRequest;
import com.project.mwork.DTO.Response.UserResponse;
import com.project.mwork.Mapper.UserMapper;
import com.project.mwork.Model.User;
import com.project.mwork.Repository.UserRepository;
import com.project.mwork.Service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements UserService{
	
	UserMapper usermapper;
	UserRepository userreposiory;
	
	@Override
	public UserResponse CreateUser(CUserRequest request) {
		if(userreposiory.existsByUsername(request.getUsername())) {
			throw new RuntimeException("Username exits");
		}
		User user = usermapper.toUser(request);
		user = userreposiory.save(user);
		return usermapper.toUserResponse(user);
	}

	@Override
	public UserResponse ReadUser(String id_user) {
		User user = userreposiory.findById(id_user).orElseThrow( () -> new RuntimeException() );
		return usermapper.toUserResponse(user);
	}

	@Override
	public UserResponse UpdateUser(CUserRequest request) {
		if (userreposiory.existsByUsername(request.getUsername())) {
			throw new RuntimeException("Cannot update user");
		}
		User user = usermapper.toUser(request);
		user = userreposiory.save(user);
		return usermapper.toUserResponse(user);
	}

	@Override
	public Boolean DeleteUser(String id_user) {
		try {
			userreposiory.delete(userreposiory.findById(id_user).orElseThrow());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public List<UserResponse> getList() {
		// TODO Auto-generated method stub
		return userreposiory.findAll().stream().map(usermapper::toUserResponse).toList();
	}
	
}
