package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.ProjectEntity;
import com.entity.UserProjectEntity;

public interface UserProjectRepository extends JpaRepository<UserProjectEntity, Integer> {


	List<UserProjectEntity> findByProjectId(Integer projectId);

	@Query(value="select count(*) from users",nativeQuery =true)
	Integer getUserCount();

	@Query(value="select count(*) from users where role = 'Developer' ",nativeQuery =true)
	Integer getDeveloperCount();

}
