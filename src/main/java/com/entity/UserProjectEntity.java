package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userProject")
public class UserProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userProjectId; 
	
	
	Integer projectId;
	Integer userId; 
	String status;
	public Integer getUserProjectId() {
		return userProjectId;
	}
	public void setUserProjectId(Integer userProjectId) {
		this.userProjectId = userProjectId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	
	
}
