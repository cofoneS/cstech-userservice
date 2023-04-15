package com.cstech.userservice.app.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import com.cstech.userservice.dao.entity.UserAddressEntity;
import com.cstech.userservice.dao.entity.UserAddressEntity_;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.entity.UserEntity_;
import com.cstech.userservice.dao.entity.UserMailEntity;
import com.cstech.userservice.dao.entity.UserMailEntity_;
import com.cstech.userservice.utility.Utility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserModel implements Serializable {
		
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
	
	public UserModel() {};
	
	public UserModel(
			Long id, 
			String firstName, 
			String surname,
			String userKey, 
			String nickname, 
			OffsetDateTime birthdate, 
			String cityOfBirth, 
			String countryOfBirth, 
			String identityDocumentCode,
			String cieCode,
			String avatar,
			String tin,
			String tinCountry,
			String vat,
			Collection<UserAddressEntity> addresses,
			Collection<UserMailEntity> mails
			) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.userKey = userKey;
		this.nickname = nickname;
		this.birthdate = Utility.doOffsetDateTime(birthdate);
		this.cityOfBirth = cityOfBirth;
		this.countryOfBirth = countryOfBirth;
		this.identityDocumentCode = identityDocumentCode;
		this.cieCode = cieCode;
		this.avatar = avatar;
		this.tin = tin;
		this.tinCountry = tinCountry;
		this.vat = vat;

		if(addresses != null && addresses.size() > 0 && addresses.stream().findFirst().isPresent()) {
			this.address = new AddressModel(addresses.stream().findFirst().get());
		}
		
		if(mails != null && mails.size() > 0 && mails.stream().findFirst().isPresent()) {
			this.mail = new MailModel(mails.stream().findFirst().get());
		}
	};
	
	public UserModel(
			Long id 
			,String firstName 
			,String surname
			,String userKey 
			,String nickname
			,OffsetDateTime birthdate 
			,String cityOfBirth
			,String countryOfBirth
			,String identityDocumentCode
			,String cieCode
			,String avatar
			,String tin
			,String tinCountry
			,String vat
			
			,String addressCityKey
			,OffsetDateTime addressEndedAt
			,String addressNote
	        ,String addressPostalCode
	        ,OffsetDateTime addressStartedAt
	        ,String addressStreet
	        ,String addressStreetNumber
            ,Long addressId

            ,Boolean mailChecked
            ,OffsetDateTime mailCheckedAt
            ,OffsetDateTime mailEndedAt
			,String mail
			,String mailNote
			,OffsetDateTime mailStartedAt
			,Long mailId
			) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.userKey = userKey;
		this.nickname = nickname;
		this.birthdate = Utility.doOffsetDateTime(birthdate);
		this.cityOfBirth = cityOfBirth;
		this.countryOfBirth = countryOfBirth;
		this.identityDocumentCode = identityDocumentCode;
		this.cieCode = cieCode;
		this.avatar = avatar;
		this.tin = tin;
		this.tinCountry = tinCountry;
		this.vat = vat;

		this.address = new AddressModel(
				addressId,
				addressStreet, 
				addressPostalCode, 
				addressStreetNumber, 
				addressCityKey, 
				addressNote, 
				Utility.doEpocTime(addressStartedAt), 
				Utility.doEpocTime(addressEndedAt)
				);
		this.mail = new MailModel(
				mailId,
				mail, 
				mailNote, 
				mailChecked, 
				Utility.doEpocTime(mailCheckedAt), 
				Utility.doEpocTime(mailStartedAt), 
				Utility.doEpocTime(mailEndedAt)
			);
		

	};	
		
	public UserModel(final UserEntity entity) {
		if(entity != null) {
			this.id = entity.getUserId();
			this.firstName = entity.getFirstName();
			this.surname = entity.getSurname();
			this.userKey = entity.getUserKey();
			this.nickname = entity.getNickname();
			this.birthdate = Utility.doOffsetDateTime(entity.getBirthdate());
			this.cityOfBirth = entity.getCityOfBirth();
			this.countryOfBirth = entity.getCountryOfBirth();
			this.identityDocumentCode = entity.getIdentityDocumentCode();
			this.cieCode = entity.getCieCode();
			this.avatar = entity.getAvatar();
			this.tin = entity.getTin();
			this.tinCountry = entity.getTinCountryKey();
			this.vat = entity.getVat();
			if(entity.getUserAddresses() != null && entity.getUserAddresses().size() > 0 && entity.getUserAddresses().stream().findFirst().isPresent()) {
				this.address = new AddressModel(entity.getUserAddresses().stream().findFirst().get());
			}
			
			if(entity.getUserMails() != null && entity.getUserMails().size() > 0 && entity.getUserMails().stream().findFirst().isPresent()) {
				this.mail = new MailModel(entity.getUserMails().stream().findFirst().get());
			}
		}
	}
	
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
