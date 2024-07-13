package com.project.mwork.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mwork.Model.Group;
import com.project.mwork.Model.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, String>{

	List<Project> findByGroup(Group group);

}
