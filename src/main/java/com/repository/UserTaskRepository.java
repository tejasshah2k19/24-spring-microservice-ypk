package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserTaskEntity;

public interface UserTaskRepository extends JpaRepository<UserTaskEntity, Integer>{

}
