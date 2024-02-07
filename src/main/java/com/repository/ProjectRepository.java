package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

}
