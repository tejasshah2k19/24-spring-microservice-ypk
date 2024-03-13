package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_task")
public class UserTaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userTaskId;
	Integer taskId;
	Integer userId;

	public Integer getUserTaskId() {
		return userTaskId;
	}

	public void setUserTaskId(Integer userTaskId) {
		this.userTaskId = userTaskId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
