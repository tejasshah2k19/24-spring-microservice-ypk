package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.ProjectEntity;
import com.entity.UserProjectEntity;

public interface UserProjectRepository extends JpaRepository<UserProjectEntity, Integer> {

	
	@Query(value = " select p.*,pu.status from projects p,user_project pu where p.project_id = pu.project_id and pu.user_id = :userId",nativeQuery = true)
	List<Object>  myProjects(Integer userId);
}
