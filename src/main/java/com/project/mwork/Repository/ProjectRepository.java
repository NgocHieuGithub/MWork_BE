package com.project.mwork.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mwork.DTO.Response.ProjectResponse;
import com.project.mwork.Model.Group;
import com.project.mwork.Model.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, String>{

	List<Project> findByGroup(Group group);
//	@Query("select new com.project.mwork.DTO.Response(p.id, p.name, p.dateCreate, p.type, p.tasks, p.docs)"
//			+ "from Project p Join p.group pg where pg.id = :id_group")
//	List<ProjectResponse> GetProjectFromGroup(@Param(value = "id_group") String id_group);
	
	@Query("select p from Project p where p.group.id = :id_group")
	List<Project> GetProjectFromGroup(@Param(value = "id_group") String id_group);
}
