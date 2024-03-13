package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByEmailAndPassword(String email, String password);

	@Query(value="select * from users where user_id in ( select user_id from user_project where project_id = :projectId)",nativeQuery = true)
	List<UserEntity> getAllUsersOfProject(Integer projectId);

	@Query(value="select * from users where user_id in ( select user_id from user_task where task_id = :taskId)",nativeQuery = true)
	List<UserEntity> getAllUsersOfTask(Integer taskId);

}
