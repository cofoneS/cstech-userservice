package com.cstech.userservice.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserModel {
		
	private Long id;
	
	@NotBlank(message = "Firstname is required.")
	@Size(min = 2, max = 255, message = "Firstname must be beetween 2 and 255 characters.")
	private String firstName;
	
	@NotBlank(message = "Lastname is required.")
	@Size(min = 2, max = 255, message = "Lastname must be beetween 2 and 255 characters.")
	private String surname;
	
	@NotBlank(message = "User key is required.")
	@Size(min = 2, max = 255, message = "User key must be beetween 2 and 255 characters.")
	private String userKey;
	
	private String nickname;
	private String birthdate;
	private String cityOfBirth;
	private String countryOfBirth;
	private String identityDocumentCode;
	private String cieCode;
	private String avatar;
	
	@NotBlank(message = "TIN key is required.")
	@Size(min = 2, max = 255, message = "TIN key must be beetween 2 and 255 characters.")	
	private String tin;
	
	@NotBlank(message = "TIN country key is required.")
	@Size(min = 2, max = 31, message = "TIN country key must be beetween 2 and 255 characters.")
	private String tinCountry;
	
	private String vat;
	
	@NotNull(message = "Address is required.")
	private AddressModel address;
	
	@NotNull(message = "MAIL is required.")
	private MailModel mail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getCityOfBirth() {
		return cityOfBirth;
	}
	public void setCityOfBirth(String cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public String getIdentityDocumentCode() {
		return identityDocumentCode;
	}
	public void setIdentityDocumentCode(String identityDocumentCode) {
		this.identityDocumentCode = identityDocumentCode;
	}
	public String getCieCode() {
		return cieCode;
	}
	public void setCieCode(String cieCode) {
		this.cieCode = cieCode;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public AddressModel getAddress() {
		return address;
	}
	public void setAddress(AddressModel address) {
		this.address = address;
	}
	public MailModel getMail() {
		return mail;
	}
	public void setMail(MailModel mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", userKey=" + userKey
				+ ", nickname=" + nickname + ", birthdate=" + birthdate + ", cityOfBirth=" + cityOfBirth
				+ ", countryOfBirth=" + countryOfBirth + ", identityDocumentCode=" + identityDocumentCode + ", cieCode="
				+ cieCode + ", avatar=" + avatar + ", tin=" + tin + ", tinCountry=" + tinCountry + ", vat=" + vat
				+ ", address=" + address + ", mail=" + mail + "]";
	}
		
}
