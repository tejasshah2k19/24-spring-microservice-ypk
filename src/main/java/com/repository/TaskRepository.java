package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

	List<TaskEntity> findByModuleId(Integer moduleId);

	@Query(value = "select * from task where task_id in (select task_id from user_task where user_id = :userId)", nativeQuery = true)
	List<TaskEntity> myTask(Integer userId);

	@Query(value = "select * from task where module_id = :moduleId and  task_id in  (select task_id from user_task where user_id = :userId)", nativeQuery = true)
	List<TaskEntity> myModuleTask(Integer userId,Integer moduleId);

	List<TaskEntity> findByStatus(String string);
}
