package com.cstech.userservice.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.cstech.userservice.utility.Utility;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	
	public static final String SCHEMA_DB_APP = "csuserapp";
  
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
	
	public BaseEntity() {
		super();
	}
	
	public BaseEntity(String userKey) {
		super();
		final Timestamp now = Utility.doTimestamp();
		enabled = true;
		createdAt = now;
		updatedBy = userKey;
		updatedAt = now;
		deletedAt = null;
	}
	
	public BaseEntity(String userKey, boolean idDeleted) {
		super();
		final Timestamp now = Utility.doTimestamp();
		enabled = ( idDeleted ? Boolean.FALSE : Boolean.TRUE);
		createdAt = now;
		updatedBy = userKey;
		updatedAt = now;
		deletedAt = ( idDeleted ? now : null);
	}	
	
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
