package com.project.mwork.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mwork.DTO.Response.TaskResponse;
import com.project.mwork.Model.Project;
import com.project.mwork.Model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, String>{

	Set<Task> findByProject(Project project);
//	@Query(value = "select t.id, t.create_at, t.dead_line, t.finish_at, t.name_task\r\n"
//			+ "from db_mwork.task t, db_mwork.project p\r\n"
//			+ "where t.project_id = p.id and p.group_id = :groupId and t.user_id = :userId ", nativeQuery = true)
//	List<TaskResponse> GetListTask(@Param("groupId") String groupId, @Param("userId") String userId);
	
	
	@Query(value = "SELECT NEW com.project.mwork.DTO.Response.TaskResponse(t.id,t.nameTask, t.deadLine, t.finishAt,t.createAt, null)\r\n"
			+ "from Task t Join t.project p\r\n"
			+ "where p.group.id = :groupId and t.user.id = :userId ")
	List<TaskResponse> GetListTask(@Param("groupId") String groupId, @Param("userId") String userId);
}
