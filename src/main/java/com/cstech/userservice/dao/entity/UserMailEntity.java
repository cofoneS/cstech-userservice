package com.cstech.userservice.dao.entity;

import java.sql.Timestamp;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cs_user_mail", schema = "userservice")
public class UserMailEntity extends BaseEntity{
	
	@Id
	@Column(name = "user_mail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userMailId;	
	
	@Basic
	@NotNull
	@Column(name = "mail", length = 1023)
	private String mail;

	@Basic
	@Column(name = "note")
	private String note;	
	
	@Basic
	@NotNull
	@Column(name = "started_at")
	private String startedAt;
	
	@Basic
	@Column(name = "ended_at")
	private Timestamp endedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false, insertable = true, updatable = false)
	private UserEntity user;	
	
}
