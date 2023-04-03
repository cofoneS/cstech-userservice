package com.cstech.userservice.dao.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseEntity {
  
    @NotNull
	@Column(name = "enabled", nullable=false)
	private Boolean enabled;		

	@CreatedDate
	@NotNull
	@Column(name = "created_at", nullable=false)
	private Timestamp createdAt;
	
	@UpdateTimestamp
	@NotNull
	@Column(name = "updated_at", nullable=false)
	private Timestamp updatedAt;
	
	@NotNull
	@Column(name = "updated_by", nullable=false)
	private String updatedBy;	
	
	@Column(name = "deleted_at")
	private Timestamp deletedAt;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}
}
