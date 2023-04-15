package com.cstech.userservice.app.model;

import java.io.Serializable;

import com.cstech.userservice.dao.entity.UserMailEntity;
import com.cstech.userservice.utility.Utility;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MailModel implements Serializable {
	
	private Long id;
	
	@NotEmpty(message = "Email is required.")
	@Email(message = "Invalid email. Try again.")
	private String mail;
	
	@Size(max = 4000, message = "Note too long!")
	private String note;
	
	private Boolean checked;
	
	private Long checkedAt;
	
	@NotBlank(message = "Started data time is required.")
	@Min(value = 9, message = "Invalid started data time.")
	private Long startedAt;
	
	private Long endedAt;
	
	public MailModel() {}
	
	public MailModel(
			Long id,
			String mail,
			String note,
			Boolean checked,
			Long checkedAt,
			Long startedAt,
			Long endedAt) {
		this.id = id;
		this.mail = mail;
		this.note = note;
		this.checked = checked;
		this.checkedAt = checkedAt;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
	}
	
	public MailModel(final UserMailEntity entity) {
		if(entity != null ) {
			this.checked = entity.getChecked();
			this.id = entity.getUserMailId();
			this.note = entity.getNote();
			this.checkedAt = Utility.doEpocTime(entity.getCheckedAt());
			this.endedAt = Utility.doEpocTime(entity.getEndedAt());
			this.mail = Utility.upperCase(entity.getMail());
			this.startedAt =  Utility.doEpocTime(entity.getStartedAt());	
		}
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getCheckedAt() {
		return checkedAt;
	}
	public void setCheckedAt(Long checkedAt) {
		this.checkedAt = checkedAt;
	}
	public Long getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Long startedAt) {
		this.startedAt = startedAt;
	}
	public Long getEndedAt() {
		return endedAt;
	}
	public void setEndedAt(Long endedAt) {
		this.endedAt = endedAt;
	}
	
	@Override
	public String toString() {
		return "MailModel [id=" + id + ", mail=" + mail + ", note=" + note + ", checked=" + checked + ", checkedAt="
				+ checkedAt + ", startedAt=" + startedAt + ", endedAt=" + endedAt + "]";
	}
	
}
