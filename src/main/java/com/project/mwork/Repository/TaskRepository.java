package com.project.mwork.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.mwork.Model.Project;
import com.project.mwork.Model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, String>{

	Set<Task> findByProject(Project project);

}
