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
	@Column(name = "checked")
	private Boolean checked;
	
	@Basic
	@Column(name = "checked_at")
	private String checkedAt;
	
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

	public Long getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(Long userMailId) {
		this.userMailId = userMailId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(String checkedAt) {
		this.checkedAt = checkedAt;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public Timestamp getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Timestamp endedAt) {
		this.endedAt = endedAt;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}	
}
