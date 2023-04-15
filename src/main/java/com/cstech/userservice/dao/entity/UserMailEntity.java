package com.cstech.userservice.dao.entity;

import java.time.OffsetDateTime;

import com.cstech.userservice.app.model.MailModel;
import com.cstech.userservice.utility.Utility;

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
@Table(name = "cs_user_mail", schema = BaseEntity.SCHEMA_DB_APP)
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
	private OffsetDateTime checkedAt;
	
	@Basic
	@NotNull
	@Column(name = "started_at")
	private OffsetDateTime startedAt;
	
	@Basic
	@Column(name = "ended_at")
	private OffsetDateTime endedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false, insertable = true, updatable = false)
	private UserEntity user;
	
	public UserMailEntity() {
		super();
	}
	
	public UserMailEntity(final String userKey, final MailModel model, final UserEntity userEntity, final boolean isDeleted) {
		super(userKey, isDeleted);
		if(model != null) {
			this.userMailId = model.getId();
			this.mail = model.getMail();
			this.note = model.getNote();
			this.checked = model.getChecked();
			this.checkedAt = Utility.epochToOffsetDateTime(model.getCheckedAt());
			this.startedAt = Utility.epochToOffsetDateTime(model.getStartedAt());
			this.endedAt = Utility.epochToOffsetDateTime(model.getEndedAt());
			this.user = userEntity;
		}
	}

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

	public OffsetDateTime getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(OffsetDateTime checkedAt) {
		this.checkedAt = checkedAt;
	}

	public OffsetDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(OffsetDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public OffsetDateTime getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(OffsetDateTime endedAt) {
		this.endedAt = endedAt;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
