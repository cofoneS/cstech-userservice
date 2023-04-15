package com.cstech.userservice.app.model;

import java.io.Serializable;

import jakarta.validation.constraints.Size;

public class UserSearchModel implements Serializable {
	
	@Size(max = 255, message = "firstName must be {max} characters.")
	private String firstName;
	
	@Size(max = 255, message = "surname must be {max} characters.")
	private String surname;
	
	@Size(max = 255, message = "userKey must be {max} characters.")
	private String userKey;
	
	@Size(max = 255, message = "nickname must be {max} characters.")
	private String nickname;
	
	@Size(max = 1023, message = "mail must be {max} characters.")
	private String mail;
	
	@Size(max = 255, message = "tin must be {max} characters.")
	private String tin;
	
	@Size(max = 31, message = "tinCountry must be {max} characters.")
	private String tinCountry;
	
	@Size(max = 63, message = "postalCode must be {max} characters.")
	private String postalCode;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getTinCountry() {
		return tinCountry;
	}
	public void setTinCountry(String tinCountry) {
		this.tinCountry = tinCountry;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
