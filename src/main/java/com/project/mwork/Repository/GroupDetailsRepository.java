package com.project.mwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mwork.Model.Group_details;
import com.project.mwork.Model.User;
import com.project.mwork.Model.Group;


public interface GroupDetailsRepository extends JpaRepository<Group_details, String>{
	boolean existsByUserAndGroup(User user, Group group);
}
