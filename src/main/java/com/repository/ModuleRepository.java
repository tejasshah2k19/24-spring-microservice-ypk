package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Integer>{

	List<ModuleEntity> findByProjectId(Integer projectId);

}
