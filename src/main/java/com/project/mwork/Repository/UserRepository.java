package com.project.mwork.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mwork.DTO.Response.UserResponse;
import com.project.mwork.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User, String>{
//	@Query(value = "select NEW com.project.mwork.DTO.Response.UserResponse(u.id, u.name, u.username, u.email, u.sdt) from db_mwork.user u, db_mwork.group_details gd where gd.user_id = u.id and gd.group_id = :groupId ", nativeQuery = true)
//	List<UserResponse> GetListUserInGroup(String groupId);
	
	@Query(value =  "SELECT NEW com.project.mwork.DTO.Response.UserResponse(u.id, u.name, u.username, u.email, u.sdt) " +
		       "from User u Join u.groups gd where gd.group.id = :groupId")
		List<UserResponse> GetListUserInGroup(@Param("groupId") String groupId);

	
	boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
}
